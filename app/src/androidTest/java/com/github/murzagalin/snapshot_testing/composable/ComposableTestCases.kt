package com.github.murzagalin.snapshot_testing.composable

import android.view.ViewGroup
import androidx.compose.runtime.Composable

interface ComposableTestCase {
    val id: String
    val width: Int
    val height: Int

    @Composable
    fun createUi()
}

val accountCardScreenshotConfig = object: ComposableTestCase {
    override val id = "Compose-AccountCard"
    override val width = ViewGroup.LayoutParams.WRAP_CONTENT
    override val height = ViewGroup.LayoutParams.WRAP_CONTENT

    @Composable
    override fun createUi() {
        AccountCard(name = "Agent Smith", position = "Secret Agent")
    }
}

val searchScreenshotConfig = object: ComposableTestCase {
    override val id = "Compose-SearchBar"
    override val width = ViewGroup.LayoutParams.WRAP_CONTENT
    override val height = ViewGroup.LayoutParams.WRAP_CONTENT

    @Composable
    override fun createUi() {
        SearchBar()
    }
}

val composableTestCases = listOf(accountCardScreenshotConfig, searchScreenshotConfig)