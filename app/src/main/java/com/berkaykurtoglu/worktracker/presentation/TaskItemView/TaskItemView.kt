package com.berkaykurtoglu.worktracker.presentation.TaskItemView

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.berkaykurtoglu.worktracker.domain.model.Task
import com.berkaykurtoglu.worktracker.util.Category
import com.berkaykurtoglu.worktracker.util.Screens

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TaskItemView(
    task : Task,
    category: Category,
    navController: NavController,
    viewModel: TaskItemViewModel = hiltViewModel(),
) {

    val haptic = LocalHapticFeedback.current

    var extanded = remember {
        mutableStateOf(false)
    }

    var user = viewModel.getCurrentUser()

    val state by remember {
        viewModel.state
    }


    /*LaunchedEffect(key1 = Unit){
        backGround.value = task.backGround.toInt()
    }*/


    Column{
        if (category == Category.FRIEND_TASK){
            FriendsTaskViewTop(task = task)
        }

        Card(
            shape = RoundedCornerShape(15.dp),
            elevation = CardDefaults.elevatedCardElevation(
                defaultElevation = 0.dp,
                pressedElevation = 10.dp,
            ),
            colors = CardDefaults.cardColors(
                containerColor = Color(task.backGround.toInt())
            ),
            modifier = Modifier.combinedClickable(
                enabled = true,
                onLongClick = {
                    extanded.value = true
                    haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                }
            ){
                if (task.documentId.isNotBlank() && !state.isCompleted){
                    navController.navigate(
                        Screens.TaskDetailScreen.route+task.documentId
                    )
                }else {
                    if (state.isCompleted){
                        Toast.makeText(navController.context,"Task is deleted",Toast.LENGTH_LONG)
                            .show()
                        return@combinedClickable
                    }
                    Toast.makeText(navController.context,"Try again later :(",Toast.LENGTH_LONG)
                        .show()
                }
            }
        ) {

            Box {

                Column(
                    modifier = Modifier
                        .padding(20.dp)
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {


                    Text(
                        text = task.title,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Medium,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Start
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = task.body,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Normal,
                        maxLines = 4,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Start

                    )

                }
            }
        }

        FriendsTaskViewBottom(task = task)

        user?.let {
            if(task.user == it){
                AnimatedVisibility(visible = extanded.value) {
                    Spacer(modifier = Modifier.height(10.dp))
                }
                DropDownView(extanded = extanded, task = task, viewModel = viewModel)
            }
        }
    }

}


@Preview
@Composable
fun PreviewItems() {

}

