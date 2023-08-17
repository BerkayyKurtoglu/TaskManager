package com.berkaykurtoglu.worktracker.presentation.TaskItemView

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.berkaykurtoglu.worktracker.domain.model.Task

@Composable
fun DropDownView(
    extanded : MutableState<Boolean>,
    task : Task,
    viewModel: TaskItemViewModel
) {

    val dropdownItems = listOf(
        DropdownItems.DeleteItem(),
    )

    DropdownMenu(expanded = extanded.value, onDismissRequest = {
        extanded.value = false
    }
    ) {

        dropdownItems.forEach {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(10.dp).clickable {
                    when(it) {
                        is DropdownItems.DeleteItem ->{
                            viewModel.onEvent(TaskItemViewEvent.Delete(task.documentId))
                        }
                    }
                    extanded.value = false
                }
            ) {
                Icon(imageVector = it.icon, contentDescription = "")
                Spacer(modifier = Modifier.width(3.dp))
                Text(text = it.text,
                    Modifier
                        .fillMaxWidth()
                )
            }

        }


    }

}