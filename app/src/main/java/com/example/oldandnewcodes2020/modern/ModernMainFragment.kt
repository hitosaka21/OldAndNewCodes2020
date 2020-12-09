package com.example.oldandnewcodes2020.modern

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.example.oldandnewcodes2020.R
import com.example.oldandnewcodes2020.SelectActivity
import com.example.oldandnewcodes2020.databinding.FragmentModernMainBinding

class ModernMainFragment : Fragment() {

    private var _binding: FragmentModernMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var lifecycleObserver: LifecycleObserver

    private var message: String? = null
    private var textView: TextView? = null

    private val activityResultLauncher =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            // Handle the return Uri
            Log.d(ModernMainFragment::class.simpleName, "return uri = $uri")
        }

    private val startForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                Log.d(ModernMainFragment::class.simpleName, "RESULT_OK")
            } else if (result.resultCode == Activity.RESULT_CANCELED) {
                Log.d(ModernMainFragment::class.simpleName, "RESULT_CANCELED")

            }
        }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        lifecycleObserver = object : LifecycleObserver {
            @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
            fun onCreated() {
                Log.d(ModernMainFragment::class.simpleName, "Activity#onCreate")

                activity?.lifecycle?.removeObserver(this) // 解除
            }
        }

        activity?.lifecycle?.addObserver(lifecycleObserver) // 登録
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        message = savedInstanceState?.getString("KEY") ?: "not save"
        Log.d(this::class.simpleName, "message=$message")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentModernMainBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textView = view.findViewById(R.id.modern_main_text_view)

        binding.modernMainButton.setOnClickListener {
            Toast.makeText(context, "SHOW TOAST", Toast.LENGTH_SHORT).show()
        }

        binding.modernGetImageUriButton.setOnClickListener {
            activityResultLauncher.launch("image/*")
        }

        binding.modernLaunchActivityButton.setOnClickListener {
            val intent = Intent(activity, SelectActivity::class.java)
            startForResult.launch(intent)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putString("KEY", "save")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
