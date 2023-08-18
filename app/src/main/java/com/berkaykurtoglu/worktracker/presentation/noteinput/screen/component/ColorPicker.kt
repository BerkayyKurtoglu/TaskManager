package com.berkaykurtoglu.worktracker.presentation.noteinput.screen.component

import android.annotation.SuppressLint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.berkaykurtoglu.worktracker.presentation.theme.BlueVariantColor
import com.berkaykurtoglu.worktracker.presentation.theme.DefaultColor
import com.berkaykurtoglu.worktracker.presentation.theme.OrangeColor
import com.berkaykurtoglu.worktracker.presentation.theme.PurpleColor

@SuppressLint("SuspiciousIndentation")
@Composable
fun ColorPicker(
    colorPickerState : MutableState<Boolean>,
    backGroundValue : MutableState<Color>
) {

    val colorList = listOf(
        DefaultColor, OrangeColor, PurpleColor, BlueVariantColor
    )

    if (colorPickerState.value){
        Dialog(
            onDismissRequest = { colorPickerState.value = false },
            properties = DialogProperties()
        ) {

            Card(
                modifier = Modifier.wrapContentHeight()
            ) {

                Column(
                    modifier = Modifier.padding(40.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(text = "Pick A Color and Enjoy !")

                    Spacer(modifier = Modifier.height(20.dp))
                    Row {
                        colorList.forEachIndexed { index, color ->
                            Canvas(
                                modifier = Modifier
                                    .size(40.dp)
                                    .clickable {
                                        backGroundValue.value = color
                                    }
                            ){
                                drawCircle(
                                    color = color
                                )
                            }
                            if (index != colorList.size-1)
                            Spacer(modifier = Modifier.width(15.dp))
                        }

                    }
                }

                }

            }

        }
    }