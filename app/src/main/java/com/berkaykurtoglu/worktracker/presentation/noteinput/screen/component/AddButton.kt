package com.berkaykurtoglu.worktracker.presentation.noteinput.screen.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.berkaykurtoglu.worktracker.R

@Composable
fun AddButton(
    modifier: Modifier,
    backGround : Color = BottomAppBarDefaults.containerColor,
    onClick : () -> Unit
) {

    ElevatedButton(
        onClick = {onClick()},
        modifier =modifier,
        contentPadding = PaddingValues(
            horizontal = 60.dp,
            vertical = 10.dp
        )
    ) {
        Icon(
            painter = painterResource(
                id = R.drawable.outline_playlist_add_check_circle_24),
            contentDescription = "",
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(text = "Add")
    }
}

private fun ifInputsEmpty(
    title : String,
    body : String,
) : Boolean{

    if (title.isBlank() or body.isBlank()) return false
    return true

}


