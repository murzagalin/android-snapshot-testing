package com.github.murzagalin.snapshot_testing.views

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.Rect
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.setPadding
import com.github.murzagalin.snapshot_testing.toIntPixel

class ScreenshotActivity : AppCompatActivity() {

    companion object {
        var config: ViewTestCase? = null

        fun newIntent(
            context: Context,
            screenConfig: ViewTestCase
        ): Intent {
            this.config = screenConfig

            return Intent(context, ScreenshotActivity::class.java)
        }
    }

    private var view: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val content = FrameLayout(this)
        content.setBackgroundColor (Color.WHITE)
        setContentView(content)
        val screenConfig = requireNotNull(config) { "screenshot config is not set" }

        content.addView(createView(screenConfig))
    }

    private fun createView(config: ViewTestCase) = FrameLayout(this).apply {
        view = config.createView(this@ScreenshotActivity)
        setPadding(16.toIntPixel(context))
        clipChildren = false
        clipToPadding = false
        layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        addView(view, ViewGroup.LayoutParams(config.width, config.height))
    }

    fun getViewRect(): Rect {
        val screenshotView = requireNotNull(view) { "the view is not initialized yet" }
        val viewPadding = 8.toIntPixel(this)

        val viewLocation = IntArray(2)
        screenshotView.getLocationInWindow (viewLocation)

        return Rect(
            viewLocation[0] - viewPadding,
            viewLocation[1] - viewPadding,
            viewLocation[0] + screenshotView.width + viewPadding,
            viewLocation[1] + screenshotView.height + viewPadding
        )
    }
}