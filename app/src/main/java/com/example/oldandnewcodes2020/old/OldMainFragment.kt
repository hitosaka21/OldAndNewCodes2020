package com.example.oldandnewcodes2020.old

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.oldandnewcodes2020.R
import kotlinx.android.synthetic.main.fragment_old_main.*

class OldMainFragment : Fragment() {
    private var message: String? = null
    private var textView: TextView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_old_main, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        message = savedInstanceState?.getString("KEY") ?: "not save"
        Log.d(this::class.simpleName, "message=$message")

        textView = view?.findViewById(R.id.old_main_text_view)

        old_main_button.setOnClickListener {
            Toast.makeText(context, "SHOW TOAST", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putString("KEY", "save")
    }
}
