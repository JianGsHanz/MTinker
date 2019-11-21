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

import com.tencent.tinker.entry.ApplicationLike
import com.tencent.tinker.lib.listener.DefaultPatchListener
import com.tencent.tinker.lib.patch.UpgradePatch
import com.tencent.tinker.lib.reporter.DefaultLoadReporter
import com.tencent.tinker.lib.reporter.DefaultPatchReporter
import com.tencent.tinker.lib.tinker.TinkerInstaller
import com.tencent.tinker.lib.util.TinkerLog

object TinkerManager {
    private val TAG = this.javaClass.simpleName

    private var isInstalled = false

    /**
     * 全部使用默认的类
     */
    fun sampleInstallTinker(appLike: ApplicationLike) {
        if (isInstalled) {
            TinkerLog.e(TAG, "已安装")
            return
        }
        TinkerInstaller.install(appLike)
        isInstalled = true

    }

    fun installTinker(appLike: ApplicationLike) {
        if (isInstalled) {
            TinkerLog.e(TAG, "已安装")
            return
        }

        TinkerInstaller.install(
            appLike,
            DefaultLoadReporter(appLike.application),
            DefaultPatchReporter(appLike.application),
            DefaultPatchListener(appLike.application),
            TinkerResultService::class.java, UpgradePatch()
        )

        isInstalled = true
    }
}
