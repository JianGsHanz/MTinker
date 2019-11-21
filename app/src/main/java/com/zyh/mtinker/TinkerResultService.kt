
package com.zyh.mtinker

import android.util.Log
import com.tencent.tinker.lib.service.DefaultTinkerResultService
import com.tencent.tinker.lib.service.PatchResult
import com.tencent.tinker.lib.util.TinkerLog
import com.tencent.tinker.lib.util.TinkerServiceInternals
import java.io.File


class TinkerResultService : DefaultTinkerResultService() {

    companion object {
        private val TAG = this.javaClass.simpleName
    }

    override fun onPatchResult(result: PatchResult?) {
        if (result == null) {
            TinkerLog.e(TAG, "result null")
            return
        }
        TinkerLog.i(TAG, "result: %s", result.toString())

        TinkerServiceInternals.killTinkerPatchServiceProcess(applicationContext)

        if (result.isSuccess) {
            //删除原文件
            deleteRawPatchFile(File(result.rawPatchFilePath))

            if (checkIfNeedKill(result)) {

                if (Utils.isBackground) {
                    Log.e(TAG,"checkIfNeedKill  Utils.isBackground = true")
                    restartProcess()
                } else {
                    Log.e(TAG,"checkIfNeedKill  Utils.isBackground = false")
                    //关闭屏幕后重启
                    Utils.ScreenState(applicationContext, object : Utils.ScreenState.IOnScreenOff {
                        override fun onScreenOff() {
                            Log.e(TAG,"checkIfNeedKill  ScreenState = true")
                            restartProcess()
                        }
                    })
                }
            } else {
                TinkerLog.i(TAG, "已经安装!")
            }
        }
    }

    private fun restartProcess() {
        android.os.Process.killProcess(android.os.Process.myPid())
    }

}
