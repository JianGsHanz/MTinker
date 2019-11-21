package com.zyh.mtinker

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.KeyEvent
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.tbruyelle.rxpermissions2.RxPermissions
import com.tencent.tinker.lib.tinker.TinkerInstaller
import java.io.File

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        requestPermission()
    }

    override fun onUserLeaveHint() {
        super.onUserLeaveHint()
        showLog("HOME键")
        //退到后台加载patch包
        startService(Intent(this,MyIntentService::class.java))
    }

    @SuppressLint("CheckResult")
    private fun requestPermission() {
        RxPermissions(this)
            .requestEach(Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
            .subscribe { t ->
                when {
                    t.granted -> {
                        // `permission.name` is granted !
                    }
                    t.shouldShowRequestPermissionRationale -> {
                        // Denied permission without ask never again
                    }
                    else -> {
                        // Denied permission with ask never again
                        // Need to go to the settings
                    }
                }
            }
    }
}
