package com.berkaykurtoglu.worktracker.presentation.taskdetail.screen.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material.icons.outlined.KeyboardArrowUp
import androidx.compose.material.pullrefresh.PullRefreshState
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.berkaykurtoglu.worktracker.domain.model.Comment
import com.berkaykurtoglu.worktracker.domain.model.Task
import com.berkaykurtoglu.worktracker.presentation.taskdetail.TaskDetailEvent
import com.berkaykurtoglu.worktracker.presentation.taskdetail.TaskDetailViewModel
import com.berkaykurtoglu.worktracker.presentation.theme.MarkAsDoneColor
import com.berkaykurtoglu.worktracker.presentation.theme.TextFieldBackGroundColor
import com.berkaykurtoglu.worktracker.util.Screens
import com.google.firebase.Timestamp

@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ActualScreen(
    task : Task,
    pullRefreshState: PullRefreshState,
    viewModel : TaskDetailViewModel = hiltViewModel(),
    documentId: String,
    navController: NavController
) {

    var commentText by remember {
        mutableStateOf("")
    }

    var user = viewModel.getCurrentUser()

    var visible by remember {
        mutableStateOf(true)
    }

    val state by remember {
        viewModel.state
    }

    if (state.isDoneCompleted){
        LaunchedEffect(key1 = Unit){
            navController.navigate(
                Screens.TabScreen.route
            ){
                popUpTo(Screens.TaskDetailScreen.route){
                    inclusive = true
                }
            }
        }
    }

    Column(modifier = Modifier.fillMaxSize()){

        LazyColumn(
            modifier = Modifier
                .pullRefresh(pullRefreshState)
                .weight(1f),
        ){

            item {
                CardItem(task = task)
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            visible = !visible
                        }
                ) {
                    Text(
                        text = "Comments (${task.comments.size})",
                        color = Color(0xFF949494),
                        modifier = Modifier
                            .padding(horizontal = 25.dp, vertical = 20.dp)
                    )
                    OutlinedIconButton(
                        onClick = {visible = !visible},
                        border = BorderStroke(1.dp,Color.Transparent),
                        colors = IconButtonDefaults.outlinedIconButtonColors(
                            contentColor = Color(0xFF949494)
                        )
                    ) {
                        Icon(
                            imageVector =
                            if (visible) Icons.Outlined.KeyboardArrowUp
                            else Icons.Outlined.KeyboardArrowDown,
                            contentDescription =""
                        )
                    }
                }

                Divider(
                    thickness = 0.5.dp
                )
            }

            itemsIndexed(task.comments){ index: Int, item: Comment ->
                AnimatedVisibility(
                    visible = visible
                ) {
                    CommentRow(comment = item)
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .imePadding()
                .padding(bottom = 20.dp, top = 10.dp, start = 20.dp, end = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            user?.let {
                if (task.user == it && !task.isMarked)
                    OutlinedIconButton(
                        onClick = {
                            viewModel.onEvent(TaskDetailEvent.MarkAsDone(task.documentId))
                        },
                        colors = IconButtonDefaults.outlinedIconButtonColors(
                            contentColor = MarkAsDoneColor
                        ),
                        border = BorderStroke(1.5.dp,color = MarkAsDoneColor)
                    ) {
                        Icon(imageVector = Icons.Outlined.Check, contentDescription = "")
                    }
            }

            Spacer(modifier = Modifier.width(5.dp))

            if (!task.isMarked) {

                OutlinedTextField(
                    value = commentText,
                    onValueChange = {
                        commentText = it
                    },
                    placeholder = {
                        Text(
                            text = "Add a comment", textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                    },
                    modifier = Modifier.weight(1f), //cover as many as you can
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent,
                        disabledBorderColor = Color.Transparent,
                        focusedContainerColor = TextFieldBackGroundColor,
                        unfocusedContainerColor = TextFieldBackGroundColor
                    ),
                    shape = RoundedCornerShape(15.dp),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        capitalization = KeyboardCapitalization.Sentences
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            //add comment
                            val user = viewModel.getCurrentUser()
                            user?.let {
                                viewModel.onEvent(
                                    TaskDetailEvent.AddComment(
                                        comment = Comment(it, commentText, Timestamp.now()),
                                        documentId
                                    )
                                )
                            }
                        }
                    )
                )

                Spacer(modifier = Modifier.width(5.dp))

                AddCommentButton(commentText) {
                    //add comment
                    user?.let {
                        viewModel.onEvent(
                            TaskDetailEvent.AddComment(
                                comment = Comment(it, commentText, Timestamp.now()),
                                documentId
                            )
                        )
                    }
                }
            }

        }


    }

}