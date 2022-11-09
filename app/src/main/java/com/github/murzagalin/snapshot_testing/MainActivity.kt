package com.github.murzagalin.snapshot_testing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.github.murzagalin.snapshot_testing.composable.AccountCard
import com.github.murzagalin.snapshot_testing.composable.SearchBar
import com.github.murzagalin.snapshot_testing.views.AccountCardView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Content()
        }
    }

    fun setViewContent() {
        setContentView(R.layout.activity_main)

        with(findViewById<AccountCardView>(R.id.account_card)) {
            name = "Agent Smith"
            position = "Secret Agent"
        }
    }
}

@Composable
fun Content() {
    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(Color.White)
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        SearchBar()

        AccountCard(name = "Agent Smith", position = "Secret Agent")
    }
}

@Preview
@Composable
fun PreviewContent() {
    Content()
}