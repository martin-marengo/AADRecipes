package com.mmarengo.android.recipes.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mmarengo.android.recipes.R

class TestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //window.requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)
        setContentView(R.layout.activity_test)


        //window.enterTransition = Explode()
        //window.allowEnterTransitionOverlap = true
    }
}
