package com.github.murzagalin.snapshot_testing.views

import android.content.Context
import android.util.AttributeSet
import android.widget.EditText
import androidx.constraintlayout.widget.ConstraintLayout
import com.github.murzagalin.snapshot_testing.R

class SearchBarView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
): ConstraintLayout(context, attrs, defStyleAttr) {

    init {
        inflate(getContext(), R.layout.view_searchbar, this)
    }

    private val queryEditText by lazy {
        findViewById<EditText>(R.id.search_query)
    }

    var query: String
        set(value) {
            queryEditText.setText(value)
        }
        get() = queryEditText.text.toString()

}