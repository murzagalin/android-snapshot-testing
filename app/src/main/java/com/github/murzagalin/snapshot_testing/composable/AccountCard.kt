package com.github.murzagalin.snapshot_testing.composable

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountCard(name: String, position: String) {
    Row(
        Modifier
            .background(
                color = Color(0xFFE8F8F8),
                shape = RoundedCornerShape(8.dp)
            )
            .padding(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            imageVector = Icons.Default.AccountBox,
            contentDescription = "Search",
            colorFilter = ColorFilter.tint(Color(0xFFBEBEBE)),
            modifier = Modifier
                .size(64.dp)
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = name,
                modifier = Modifier.padding(4.dp),
                fontSize = 16.sp,
                color = Color.Black
            )

            Text(
                text = position,
                modifier = Modifier.padding(4.dp),
                fontSize = 14.sp,
                color = Color(0xFFAAAAAA)
            )
        }
    }
}

@Preview
@Composable
fun PreviewAccountCard() {
    AccountCard("Agent Smith", "Secret Agent")
}