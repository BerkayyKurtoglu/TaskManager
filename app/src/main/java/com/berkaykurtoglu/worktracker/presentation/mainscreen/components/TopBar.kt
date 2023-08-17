package com.berkaykurtoglu.worktracker.presentation.mainscreen.components

import androidx.compose.foundation.background
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    name : String = "YGDB71",
) {

    CenterAlignedTopAppBar(
        modifier = modifier.background(Color.Transparent),
        title = {}
    )
}