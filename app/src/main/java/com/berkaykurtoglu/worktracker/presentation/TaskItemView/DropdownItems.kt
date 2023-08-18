package com.berkaykurtoglu.worktracker.presentation.TaskItemView

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.ui.graphics.vector.ImageVector

sealed class DropdownItems(val text: String, val icon : ImageVector){

    class DeleteItem() : DropdownItems("Delete",Icons.Outlined.Delete)


}
