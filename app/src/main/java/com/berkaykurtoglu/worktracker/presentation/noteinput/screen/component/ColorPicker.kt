package com.berkaykurtoglu.worktracker.presentation.noteinput.screen.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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

@Composable
fun ColorPicker(
    colorPickerState : MutableState<Boolean>,
    backGroundValue : MutableState<Color>
) {

    if (colorPickerState.value){
        Dialog(
            onDismissRequest = { colorPickerState.value = false },
            properties = DialogProperties()
        ) {

            Card {

                Column(
                    modifier = Modifier.padding(40.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(text = "Pick A Color and Enjoy !")

                    Spacer(modifier = Modifier.height(20.dp))

                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Canvas(
                            modifier = Modifier.size(40.dp).clickable {
                                backGroundValue.value = DefaultColor
                            }
                        ){
                            drawCircle(
                                color = DefaultColor
                            )
                        }

                        Spacer(modifier = Modifier.width(15.dp))

                        Canvas(modifier = Modifier.size(40.dp).clickable {
                            backGroundValue.value = OrangeColor
                        }
                        ){
                            drawCircle(
                                color = OrangeColor
                            )
                        }

                        Spacer(modifier = Modifier.width(15.dp))

                        Canvas(modifier = Modifier.size(40.dp).clickable {
                            backGroundValue.value = PurpleColor
                        }
                        ){
                            drawCircle(
                                color = PurpleColor
                            )
                        }
                        Spacer(modifier = Modifier.width(15.dp))

                        Canvas(modifier = Modifier.size(40.dp).clickable {
                            backGroundValue.value = BlueVariantColor
                        }
                        ){
                            drawCircle(
                                color = BlueVariantColor
                            )
                        }

                    }

                }

            }

        }
    }

}