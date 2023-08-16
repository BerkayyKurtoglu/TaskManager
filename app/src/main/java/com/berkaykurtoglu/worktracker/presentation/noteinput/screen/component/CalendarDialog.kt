package com.berkaykurtoglu.worktracker.presentation.noteinput.screen.component

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.maxkeppeker.sheets.core.models.base.UseCaseState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarConfig
import com.maxkeppeler.sheets.calendar.models.CalendarSelection

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun datePicker(
    calendarState : UseCaseState
) : String {

    var date by remember {
        mutableStateOf("")
    }


    CalendarDialog(
        state = calendarState ,
        selection = CalendarSelection.Date{
            date = "${it.dayOfMonth} ${it.month}"
        },
        config = CalendarConfig(
            monthSelection = true,
            yearSelection = true
        ),
    )
    return date
}