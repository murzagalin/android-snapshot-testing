package com.github.murzagalin.snapshot_testing.composable

import android.content.Context
import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.view.ViewGroup
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.github.murzagalin.snapshot_testing.toIntPixel

class ScreenshotComposeActivity  : AppCompatActivity() {

    companion object {
        private val CONFIG_EXTRA = "screenshot config"
        fun newIntent(
            context: Context,
            screenConfig: ComposableTestCase
        ) = Intent(context, ScreenshotComposeActivity::class.java).apply {
            putExtra(CONFIG_EXTRA, screenConfig)
        }
    }

    private val config by lazy {
        intent.getSerializableExtra(CONFIG_EXTRA, ComposableTestCase::class.java)
    }

    private var viewRect: Rect? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val screenConfig = requireNotNull(config) { "screenshot config is not set" }

        setContent {
            Box(
                modifier = Modifier
                    .padding(16.dp)
                    .background(Color.White)
                    .setWidth(screenConfig.width)
                    .setHeight(screenConfig.height)
                    .onGloballyPositioned { coordinates ->
                        val position = coordinates.positionInWindow()
                        val size = coordinates.size
                        viewRect = Rect(
                            position.x.toInt(),
                            position.y.toInt(),
                            (position.x + size.width).toInt(),
                            (position.y + size.height).toInt()
                        )
                    }
            ) {
                screenConfig.createUi()
            }
        }
    }

    private fun Modifier.setWidth(value: Int) = when (value) {
        ViewGroup.LayoutParams.MATCH_PARENT -> fillMaxWidth()
        ViewGroup.LayoutParams.WRAP_CONTENT -> this
        else -> width(value.dp)
    }

    private fun Modifier.setHeight(value: Int) = when (value) {
        ViewGroup.LayoutParams.MATCH_PARENT -> fillMaxHeight()
        ViewGroup.LayoutParams.WRAP_CONTENT -> this
        else -> height(value.dp)
    }

    fun getViewRect(): Rect {
        val viewRect = requireNotNull(viewRect) { "the composable is not initialized yet" }
        val viewPadding = 8.toIntPixel(this)

        return Rect(
            (viewRect.left - viewPadding),
            (viewRect.top - viewPadding),
            (viewRect.right + viewPadding),
            (viewRect.bottom + viewPadding)
        )
    }
}
