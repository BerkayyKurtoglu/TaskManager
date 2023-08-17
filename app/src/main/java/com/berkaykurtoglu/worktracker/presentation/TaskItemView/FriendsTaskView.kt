package com.berkaykurtoglu.worktracker.presentation.TaskItemView

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.berkaykurtoglu.worktracker.domain.model.Task

@Composable
fun FriendsTaskViewTop(
    task : Task
) {

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Canvas(modifier = Modifier.size(30.dp)){
            drawCircle(
                color = Color(0xFFEEEEEE)
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = task.user,
            fontSize = 8.4.sp,
            color = Color(0xFF8F8F8F),
            fontWeight = FontWeight.Normal,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.width(110.dp)
        )
    }
    Spacer(modifier = Modifier.height(5.dp))
}

@Composable
fun FriendsTaskViewBottom(
    task: Task
) {

    val count = task.comments.size
    if (count != 0){


        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
        ) {

            /*Canvas(modifier = Modifier.size(20.dp)){
                drawCircle(color = Color(0xFFEEEEEE))
            }
            Spacer(modifier = Modifier.width(3.dp))

            Canvas(modifier = Modifier.size(20.dp)){
                drawCircle(color = Color(0xFFEEEEEE))
            }
            Spacer(modifier = Modifier.width(3.dp))

            Canvas(modifier = Modifier.size(20.dp)){
                drawCircle(color = Color(0xFFEEEEEE))
            }
            if (count > 3) {
                Spacer(modifier = Modifier.width(3.dp))
                Text(text = "...", color = Color(0xFFEEEEEE))
            }*/
        }

    }

}

