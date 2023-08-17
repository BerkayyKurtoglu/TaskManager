package com.berkaykurtoglu.worktracker.presentation.taskdetail.screen.component

import android.annotation.SuppressLint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.berkaykurtoglu.worktracker.domain.model.Comment
import java.text.SimpleDateFormat
import java.util.Date

@SuppressLint("SimpleDateFormat")
@Composable
fun CommentRow(
    comment: Comment
) {

    val dateFormat = SimpleDateFormat("E, MMM d")
    val date = Date(comment.date.seconds*1000 + comment.date.nanoseconds/1000000)


    Row(
        Modifier.padding(top = 25.dp, start = 20.dp, end = 20.dp)
    ) {

        Canvas(modifier = Modifier
            .size(42.dp)
            .padding(top = 5.dp)){
            drawCircle(
                color = Color(0xFFEEEEEE)
            )
        }
        Spacer(modifier = Modifier.width(7.dp) )

        Column {

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                //Image Will Come

                Text(
                    text = comment.user,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 13.sp,
                    modifier = Modifier.width(120.dp),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier
                    .height(0.dp)
                    .weight(1f))
                Text(
                    text = dateFormat.format(date),
                    fontWeight = FontWeight.Normal,
                    color = Color(0xFFC9C9C9),
                    maxLines = 1,
                    fontSize = 12.sp
                )

            }

            Spacer(modifier = Modifier.height(8.dp))


            Text(
                text = comment.body,
                fontSize = 15.sp)

            Spacer(modifier = Modifier.height(10.dp))

        }



    }

}

@Preview(showBackground = true)
@Composable
fun CommentRowPrev() {
    CommentRow(Comment())
}

