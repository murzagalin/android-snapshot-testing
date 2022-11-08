package com.github.murzagalin.snapshot_testing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
}