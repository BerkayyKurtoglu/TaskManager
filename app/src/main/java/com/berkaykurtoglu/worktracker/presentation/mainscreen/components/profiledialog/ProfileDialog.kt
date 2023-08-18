package com.berkaykurtoglu.worktracker.presentation.mainscreen.components.profiledialog

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.berkaykurtoglu.worktracker.domain.model.User
import com.berkaykurtoglu.worktracker.presentation.mainscreen.MainScreenState

@Composable
fun ProfileDialog(
    dialogVisible : MutableState<Boolean>,
    dialogState: MainScreenState
) {

    if (dialogVisible.value) {
        Dialog(
            onDismissRequest = {
                dialogVisible.value = false
            },
            properties = DialogProperties(),
        ) {

            Card(
                modifier = Modifier.size(400.dp)
            ) {

                Box(
                    modifier = Modifier.padding(25.dp)
                ) {


                    if (dialogState.dialogIsLoading) CircularProgressIndicator()
                    if (dialogState.dialogError.isNotBlank()) Text(text = dialogState.dialogError)

                    if (
                        !dialogState.dialogIsLoading and dialogState.dialogError.isBlank()
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Canvas(modifier = Modifier.size(35.dp)) {
                                drawCircle(Color(0xFFB1B1B1))
                            }
                            Text(
                                text = "Name : ${dialogState.dialogUser.name} ${dialogState.dialogUser.lastName}"
                            )
                            Spacer(modifier = Modifier.height(7.dp))
                            Text(
                                text = "E-mail : ${dialogState.dialogUser.email}"
                            )
                            Spacer(modifier = Modifier.height(7.dp))
                            Text(
                                text = "Department : ${dialogState.dialogUser.departmant}"
                            )


                        }
                    }

                }

            }

        }
    }
}