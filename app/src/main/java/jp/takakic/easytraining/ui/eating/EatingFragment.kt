package jp.takakic.easytraining.ui.eating

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.support.v4.app.Fragment
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import jp.takakic.easytraining.R

class EatingFragment : Fragment() {

    private lateinit var eatingViewModel: EatingViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        eatingViewModel =
            ViewModelProviders.of(this).get(EatingViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_eating, container, false)
        val textView: TextView = root.findViewById(R.id.text_eating)
        eatingViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}