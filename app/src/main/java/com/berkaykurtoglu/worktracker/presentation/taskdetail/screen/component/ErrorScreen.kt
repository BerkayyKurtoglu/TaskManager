package com.berkaykurtoglu.worktracker.presentation.taskdetail.screen.component

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.berkaykurtoglu.worktracker.presentation.taskdetail.TaskDetailViewModel
import com.google.firebase.firestore.DocumentId

@Composable
fun ErrorScreen(
    message : String,
    viewModel : TaskDetailViewModel = hiltViewModel(),
    documentId: String
) {

    Text(
        text = message,
        fontSize = 25.sp
    )
    Button(onClick = {
        viewModel.getTaskDetail(documentId)
    }) {
        Text(text = "Try Again")
    }



}