package com.zyh.mtinker

import android.content.Context
import android.util.Log
import android.widget.Toast

/**
 *Time:2019/11/21
 *Author:zyh
 *Description:扩展类
 */
fun Context.showToast(content: String):Toast{
    val toast = Toast.makeText(MTinkerApplicationLike.context, content, Toast.LENGTH_SHORT)
    toast.show()
    return toast
}

fun Context.showLog(content: String){
    Log.e("MTinker",content)
}