package com.berkaykurtoglu.worktracker.presentation.mainscreen.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@Composable
fun FloatingAction(
    onClick: () -> Unit
) {

    FloatingActionButton(
        onClick = {onClick()},
        containerColor = BottomAppBarDefaults.bottomAppBarFabColor,
        elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation(0.dp),
    ) {
        Icon(imageVector = Icons.Filled.Add, contentDescription = "Add Task")
    }

}

