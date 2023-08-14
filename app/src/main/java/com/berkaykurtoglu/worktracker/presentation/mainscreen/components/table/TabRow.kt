package com.berkaykurtoglu.worktracker.presentation.mainscreen.components.table

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.berkaykurtoglu.worktracker.presentation.friendstask.screen.FriendsScreen
import com.berkaykurtoglu.worktracker.presentation.mainscreen.screen.component.table.TabItem
import com.berkaykurtoglu.worktracker.presentation.yourtask.screen.YourTaskScreen
import com.berkaykurtoglu.worktracker.presentation.yourtask.screen.components.FilterChips
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
            icon = Icons.Outlined.Home,
            screen = { YourTaskScreen(navController = navController) }
        ),
        TabItem(
            title = "Friend's Tasks",
            icon = Icons.Outlined.Person,
            screen = { FriendsScreen(navController = navController) }
        )

    )

        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            TabRow(
                selectedTabIndex = pagerState.currentPage,
            ) {

                tabs.forEachIndexed { index, tabItem ->
                    Tab(
                        selected = index == pagerState.currentPage,
                        onClick = {
                            coroutineScope.launch{
                                pagerState.animateScrollToPage(index)
                            }
                        },
                        text = {Text(text = tabItem.title )},
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