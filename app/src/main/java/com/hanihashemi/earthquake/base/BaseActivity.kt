package com.hanihashemi.earthquake.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    protected abstract val layoutResource: Int

    open fun gatherArguments(bundle: Bundle) {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutResource)
        if (intent.extras != null)
            gatherArguments(intent.extras)
        customizeUI()
    }

    protected abstract fun customizeUI()
}