package jp.takakic.easytraining

import java.io.Serializable
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

open class body : RealmObject(), Serializable {
    var weight: Double = 0.0 //体重
    var fat: Double = 0.0 //体脂肪
    var muscle: Double = 0.0 //筋肉量
    var chest: Int = 0 //胸囲
    var waist: Int = 0 //ウエスト
    var shoulder: Int = 0 //肩幅
    var neck: Int = 0 //首
    var humerusLeft: Int = 0 //上腕左
    var humerusRight: Int = 0 //上腕右
    var forearmLeft: Int = 0 //前腕左
    var forearmRight: Int = 0 //前腕右
    var thighLeft: Int = 0 //太もも左
    var thighRight: Int = 0 //太もも右
    var calfLeft: Int = 0 //ふくらはぎ左
    var calfRight: Int = 0 //ふくらはぎ右

    var date: String = "" //入力日次

    @PrimaryKey
    var id: Int = 0


}


