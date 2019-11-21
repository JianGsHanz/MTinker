/*
 * Tencent is pleased to support the open source community by making Tinker available.
 *
 * Copyright (C) 2016 THL A29 Limited, a Tencent company. All rights reserved.
 *
 * Licensed under the BSD 3-Clause License (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 *
 * https://opensource.org/licenses/BSD-3-Clause
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" basis, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.zyh.mtinker

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Environment
import android.os.StatFs

import com.tencent.tinker.lib.util.TinkerLog
import com.tencent.tinker.loader.shareutil.ShareConstants

import java.io.ByteArrayOutputStream
import java.io.File
import java.io.IOException
import java.io.PrintStream

object Utils {

    var isBackground = false

    class ScreenState(context: Context, onScreenOffInterface: IOnScreenOff?) {
        interface IOnScreenOff {
            fun onScreenOff()
        }

        init {
            val filter = IntentFilter()
            filter.addAction(Intent.ACTION_SCREEN_OFF)

            context.registerReceiver(object : BroadcastReceiver() {

                override fun onReceive(context: Context, intent: Intent?) {
                    val action = if (intent == null) "" else intent.action
                    if (Intent.ACTION_SCREEN_OFF == action) {
                        onScreenOffInterface?.onScreenOff()
                    }
                    context.unregisterReceiver(this)
                }
            }, filter)
        }
    }
}
