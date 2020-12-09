package com.example.oldandnewcodes2020.old

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.oldandnewcodes2020.R
import com.example.oldandnewcodes2020.SelectActivity
import kotlinx.android.synthetic.main.fragment_old_main.*

class OldMainFragment : Fragment() {
    private val REQUEST_CODE_GET_IMAGE = 1
    private val REQUEST_CODE_LAUNCH_ACTIVITY = 2

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

        old_get_image_uri_button.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT).apply {
                type = "image/*"
            }
            startActivityForResult(intent, REQUEST_CODE_GET_IMAGE)
        }

        old_launch_activity_button.setOnClickListener {
            val intent = Intent(activity, SelectActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE_LAUNCH_ACTIVITY)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putString("KEY", "save")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE_GET_IMAGE && resultCode == Activity.RESULT_OK) {
            Log.d(this::class.simpleName, "return uri = ${data?.data}")
        }

        if (requestCode == REQUEST_CODE_LAUNCH_ACTIVITY) {
            if (resultCode == Activity.RESULT_OK) {
                Log.d(this::class.simpleName, "RESULT_OK")
            } else if (resultCode == Activity.RESULT_CANCELED) {
                Log.d(this::class.simpleName, "RESULT_CANCELED")
            }
        }
    }
}
