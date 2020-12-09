package com.example.oldandnewcodes2020.modern

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract
import com.example.oldandnewcodes2020.SelectActivity

class CustomActivityResultContract : ActivityResultContract<Unit, CustomResult>() {
    override fun createIntent(context: Context, input: Unit?): Intent =
        Intent(context, SelectActivity::class.java)

    override fun parseResult(resultCode: Int, intent: Intent?): CustomResult {
        // some codes
        return CustomResult(resultCode == Activity.RESULT_OK)
    }
}