package com.berkaykurtoglu.worktracker.presentation.taskdetail.screen.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun AddCommentButton(
    commentText : String,
    modifier : Modifier  = Modifier,
    onClicked : () -> Unit
) {

    OutlinedIconButton(
        onClick = onClicked,
        enabled = commentText.isNotBlank(),
        modifier = modifier
    ) {
        Icon(imageVector = Icons.Outlined.KeyboardArrowUp, contentDescription = "")
    }

}