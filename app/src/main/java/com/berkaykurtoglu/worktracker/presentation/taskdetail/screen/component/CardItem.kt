package com.berkaykurtoglu.worktracker.presentation.taskdetail.screen.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.berkaykurtoglu.worktracker.domain.model.Task

@Composable
fun CardItem(
    task: Task
) {

    Card(
        modifier = Modifier.padding(horizontal = 25.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5))
    ) {
        Column(
            Modifier.padding(top = 20.dp, start = 20.dp, end = 20.dp, bottom = 20.dp)
        ) {

            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = task.user,
                    fontSize = 13.sp,
                    color = Color(0xFF161616),
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .width(120.dp)
                )
                Spacer(modifier = Modifier
                    .height(0.dp)
                    .weight(1f))
                Text(
                    text = "⏳ ${task.deadLine.lowercase()}",
                    modifier = Modifier.padding(bottom = 10.dp),
                    fontSize = 13.sp,
                    color = Color(0xFFA2A2A2)
                )
            }

            Spacer(modifier = Modifier.height(5.dp))

            ElevatedCard(
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.elevatedCardColors(
                    containerColor = Color(task.backGround.toInt())
                )
            ) {

                Column(
                    modifier = Modifier
                        .padding(25.dp)
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.Start
                ) {

                    Row {
                        Spacer(modifier = Modifier
                            .height(0.dp)
                            .weight(1f))
                    }

                    Text(
                        text = task.title,
                        fontSize = 20.sp,
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
    }




}