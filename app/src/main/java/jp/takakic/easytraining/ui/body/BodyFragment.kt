package jp.takakic.easytraining.ui.body

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.support.v4.app.Fragment
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.util.Log
import jp.takakic.easytraining.R
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import io.realm.Realm
import io.realm.RealmChangeListener
import jp.takakic.easytraining.body
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.input_body.*
import java.text.SimpleDateFormat
import io.realm.annotations.PrimaryKey
import java.util.*

class BodyFragment : Fragment() {
    private lateinit var mRealm: Realm
    private val mRealmListener = object : RealmChangeListener<Realm> {
        override fun onChange(element: Realm) {

        }
    }

    private lateinit var bodyViewModel: BodyViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bodyViewModel =
            ViewModelProviders.of(this).get(BodyViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_body, container, false)
        //val textView: TextView = root.findViewById(R.id.text_body)
        //bodyViewModel.text.observe(this, Observer {
        //    textView.text = it
        //})


        return root
    }

    override fun onStart() {
        super.onStart()
        var todayBody: body? = null
        var s = 0
        var df = SimpleDateFormat("yyyy-MM-dd")
        var today = Date()
        var setDay = df.format(today)
        Log.d("testeset", "日付；　" + today.toString())

        bodyDate.text = setDay.toString()

        // Realmの設定
        mRealm = Realm.getDefaultInstance()
        mRealm.addChangeListener(mRealmListener)

        //Realmに今日の日付のデータが有るかを確認
        val mBody = mRealm.where(body::class.java).equalTo("date", setDay).findFirst()

        //今日の日付を確認。Realmにまだその日のデータが生成されていなければ、新規登録。あれば、呼び出し
        if (mBody != null) {
            //データ呼び出し。Realm各項目の値を取り出し、テキストエディットに代入
            s = 1 //登録有無セット

            val todayWeight = mBody.weight.toString()
            val todayFat = mBody.fat.toString()
            val todayMuscle = mBody.muscle.toString()

            weightInputEditText.setText(todayWeight)
            fatInputEditText.setText(todayFat)
            muscleInputEditText.setText(todayMuscle)

        } else {
            //新規
            s = 2 //登録有無セット

            todayBody = body()

            val allBodyData = mRealm.where(body::class.java).findAll()

            val identifier: Int =
                if (allBodyData.max("id") != null) {
                    allBodyData.max("id")!!.toInt() + 1
                } else {
                    0
                }

            todayBody!!.id = identifier




            //ボタンを取得
            val btn = activity!!.findViewById<View>(R.id.registrate)

            //登録ボタンが押されたらRealmに書くテキストエディットに入っているデータを挿入する
            btn.setOnClickListener {
                val inputWeight = weightInputEditText.text.toString() //体重入力値を取得
                val inputFat = fatInputEditText.text.toString() //体脂肪入力値を取得
                val inputMuscle = muscleInputEditText.text.toString() //筋肉量入力値を取得
                Log.d("testtest", inputWeight)

                //新規か更新を確認して、登録
                if (s == 1) {
                    val target = mRealm.where(body::class.java).equalTo("date", today).findFirst()

                    mRealm.executeTransaction {
                        target?.weight = inputWeight.toDouble()
                        target?.fat = inputFat.toDouble()
                        target?.muscle = inputMuscle.toDouble()
                    }
                } else {
                    todayBody?.weight = inputWeight.toDouble()
                    todayBody?.fat = inputFat.toDouble()
                    todayBody?.muscle = inputMuscle.toDouble()
                    todayBody!!.date = setDay
                    mRealm.beginTransaction()
                    mRealm.copyToRealmOrUpdate(todayBody)
                    mRealm.commitTransaction()
                }
            }

            //戻るを取得
            val backBtn = activity!!.findViewById<View>(R.id.backButton)

            //backBtnが押されたら前の日付のデータを取得
            backBtn.setOnClickListener {
                val cal = Calendar.getInstance()
                cal.time = today
                cal.add(Calendar.DATE, -1)
                today = cal.time

                Log.d("testtest", "ボタン押したあとの日付；　" + today)

            }

        }
    }

    override fun onResume() {
        super.onResume()


    }

    private fun addBodyForTest() {
        val body = body()
        body.weight = 60.0
        body.fat = 14.3
        body.muscle = 45.5
        body.id = 0
        body.date = "2020-04-25"
        mRealm.beginTransaction()
        mRealm.copyToRealmOrUpdate(body)
        mRealm.commitTransaction()
    }

    override fun onDestroy() {
        super.onDestroy()
        mRealm.close()
    }

}


