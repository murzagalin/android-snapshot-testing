package com.github.murzagalin.snapshot_testing.views

import android.content.Context
import android.view.View
import android.view.ViewGroup

interface ViewTestCase {
    val id: String
    val width: Int
    val height: Int

    fun createView(context: Context): View
}

val accountCardTestCase = object: ViewTestCase {
    override val id = "AccountCard"
    override val width = ViewGroup.LayoutParams.WRAP_CONTENT
    override val height = ViewGroup.LayoutParams.WRAP_CONTENT

    override fun createView(context: Context) = AccountCardView(context).apply {
        name = "Agent Smith"
        position = "Secret Agent"
    }
}

val searchBarTestCase = object: ViewTestCase {
    override val id = "SearchBar"
    override val width = ViewGroup.LayoutParams.WRAP_CONTENT
    override val height = ViewGroup.LayoutParams.WRAP_CONTENT

    override fun createView(context: Context) = SearchBarView(context)
}

val viewTestCases = listOf(accountCardTestCase, searchBarTestCase)
