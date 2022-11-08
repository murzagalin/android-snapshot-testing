package com.github.murzagalin.snapshot_testing.composable

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar() {
    var text by rememberSaveable { mutableStateOf("") }
    Row(
        Modifier
            .shadow(
                elevation = 2.dp,
                shape = RoundedCornerShape(8.dp)
            )
            .background(
                color = Color(0xFFEFEFEF),
                shape = RoundedCornerShape(8.dp)
            )
            .padding(8.dp)
    ) {
        Image(
            imageVector = Icons.Default.Search,
            contentDescription = "Search",
            colorFilter = ColorFilter.tint(Color(0xFFBEBEBE)),
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.CenterVertically)
                .size(32.dp)
        )

        TextField(
            value = text,
            onValueChange = {
                text = it
            },
            label = { Text("Type your search query here") },
            maxLines = 1,
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.Transparent
            )
        )
    }
}

@Preview
@Composable
fun PreviewMessageCard() {
    SearchBar()
}