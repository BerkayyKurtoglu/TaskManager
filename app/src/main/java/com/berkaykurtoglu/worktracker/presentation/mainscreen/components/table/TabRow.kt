package com.berkaykurtoglu.worktracker.presentation.mainscreen.components.table

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DatasetLinked
import androidx.compose.material.icons.outlined.FormatAlignLeft
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.PeopleAlt
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Task
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.berkaykurtoglu.worktracker.presentation.friendstask.FriendsScreen
import com.berkaykurtoglu.worktracker.presentation.mainscreen.screen.component.table.TabItem
import com.berkaykurtoglu.worktracker.presentation.yourtask.screen.components.YourTaskScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TableRow(
    pagerState: PagerState = rememberPagerState(),
    navController: NavController,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    paddingValues: PaddingValues
) : Int {

    val tabs = listOf(
        TabItem(
            title = "Your Tasks",
            icon = Icons.Outlined.Task,
            screen = { YourTaskScreen(navController = navController) }
        ),
        TabItem(
            title = "Friend's Tasks",
            icon = Icons.Outlined.FormatAlignLeft,
            screen = { FriendsScreen(navController = navController) }
        )

    )

        Column(
        ) {
            TabRow(
                selectedTabIndex = pagerState.currentPage,
                indicator = {
                    if (pagerState.currentPage < it.size){
                        TabRowDefaults.Indicator(
                            modifier = Modifier
                                .tabIndicatorOffset(
                                    it[pagerState.currentPage]
                                ),
                            height = 2.dp
                        )
                    }
                },
                modifier = Modifier.focusable(false)
            ) {

                tabs.forEachIndexed { index, tabItem ->
                    Tab(
                        selected = index == pagerState.currentPage,
                        onClick = {
                            coroutineScope.launch{
                                pagerState.animateScrollToPage(index)
                            }
                        },
                        icon = { Icon(imageVector = tabItem.icon, contentDescription = "")}
                    )
                }

            }

            HorizontalPager(
                pageCount = tabs.size,
                state = pagerState
            ) {
                tabs[it].screen()
            }
        }


    return pagerState.currentPage


}