package com.github.murzagalin.snapshot_testing.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun BoxWithBorders(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .border(2.dp, Color.Red)
            .padding(8.dp)
            .border(2.dp, Color.Blue)
            .background(Color.White)
            .padding(8.dp)
            .size(48.dp)
    )
}

@Preview
@Composable
fun PreviewBoxWithBorders() {
    BoxWithBorders()
}