package com.zyh.mtinker

import android.app.IntentService
import android.content.Intent
import android.content.Context
import android.os.Environment
import android.widget.Toast
import com.tencent.tinker.lib.tinker.TinkerInstaller
import java.io.File

/**
 * An [IntentService] subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * helper methods.
 */
class MyIntentService : IntentService("MyIntentService") {

    override fun onHandleIntent(intent: Intent?) {
        val path = Environment.getExternalStorageDirectory().path + File.separator+ "patch_signed_7zip.apk"
        val file = File(path)
        if (file.exists()){
            showLog("补丁已经存在")
            TinkerInstaller.onReceiveUpgradePatch(this,path)
        }else{
            showLog("补丁不存在")
        }
    }
}
