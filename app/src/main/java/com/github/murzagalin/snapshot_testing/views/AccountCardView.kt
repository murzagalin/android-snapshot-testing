package com.github.murzagalin.snapshot_testing.views

import android.content.Context
import android.util.AttributeSet
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.github.murzagalin.snapshot_testing.R

class AccountCardView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
): ConstraintLayout(context, attrs, defStyleAttr) {

    init {
        inflate(getContext(), R.layout.view_account, this)
    }

    private val nameTextView by lazy {
        findViewById<TextView>(R.id.name)
    }

    private val positionTextView by lazy {
        findViewById<TextView>(R.id.position)
    }

    var name: String
        set(value) {
            nameTextView.text = value
        }
        get() = nameTextView.text.toString()

    var position: String
        set(value) {
            positionTextView.text = value
        }
        get() = positionTextView.text.toString()

}