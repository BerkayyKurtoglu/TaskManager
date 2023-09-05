package com.berkaykurtoglu.worktracker.presentation.mainscreen.components.table

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

data class TabItem(
    val title : String,
    val icon : ImageVector,
    val screen : @Composable () -> Unit
)
