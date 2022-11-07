package com.github.murzagalin.snapshot_testing

import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.view.ViewGroup
import androidx.core.view.marginStart
import androidx.core.view.setMargins
import androidx.core.view.updateLayoutParams
import com.github.murzagalin.snapshot_testing.views.AccountCardView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        with(findViewById<AccountCardView>(R.id.account_card)) {
            name = "Agent Smith"
            position = "Secret Agent"
        }
    }



    private val Number.px get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this.toFloat(),
        Resources.getSystem().displayMetrics
    ).toInt()
}