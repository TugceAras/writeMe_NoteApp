package com.tugcearas.writeme.util

import android.content.Context
import android.view.View
import android.widget.Toast

fun View.click(func:()->Unit){
    this.setOnClickListener {
        func()
    }
}

fun Context.toastMessage(message:String) = Toast.makeText(this,message,Toast.LENGTH_SHORT).show()