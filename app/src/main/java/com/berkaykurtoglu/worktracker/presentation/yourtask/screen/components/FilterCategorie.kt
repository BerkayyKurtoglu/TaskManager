package com.berkaykurtoglu.worktracker.presentation.yourtask.screen.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Done
import androidx.compose.ui.graphics.vector.ImageVector

sealed class FilterCategorie(val title : String, val icon : ImageVector){

    object MarkedChip : FilterCategorie("Done",Icons.Outlined.Done)
    object UnMarkedChip : FilterCategorie("Undone",Icons.Outlined.DateRange)


}
