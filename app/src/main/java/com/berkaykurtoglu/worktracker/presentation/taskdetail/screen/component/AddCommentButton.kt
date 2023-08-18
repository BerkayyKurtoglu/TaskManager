package com.berkaykurtoglu.worktracker.presentation.taskdetail.screen.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowUpward
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun AddCommentButton(
    commentText : String,
    modifier : Modifier  = Modifier,
    onClicked : () -> Unit
) {

    FilledTonalIconButton(
        onClick = onClicked,
        enabled = commentText.isNotBlank(),
        modifier = modifier
    ) {
        Icon(imageVector = Icons.Outlined.ArrowUpward, contentDescription = "")
    }

}