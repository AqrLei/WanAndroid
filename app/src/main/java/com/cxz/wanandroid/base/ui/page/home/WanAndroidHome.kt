package com.cxz.wanandroid.base.ui.page.home

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.cxz.wanandroid.R
import com.cxz.wanandroid.base.ui.theme.ColorPrimary
import com.cxz.wanandroid.ui.activity.OnArticleItemClicked
import com.google.accompanist.insets.navigationBarsHeight
import com.google.accompanist.insets.statusBarsPadding
import kotlinx.coroutines.launch

/**
 * created by AqrLei on 2022/2/11
 */
sealed class WanAndroidScreen(
    val route: String,
    @StringRes val stringResId: Int,
    @DrawableRes val drawableResId: Int
) {
    object Home : WanAndroidScreen("home", R.string.home, R.drawable.ic_home_black_24dp)
    object Square : WanAndroidScreen("square", R.string.square, R.drawable.ic_square_black_24dp)
    object Wechat : WanAndroidScreen("wechat", R.string.wechat, R.drawable.ic_wechat_black_24dp)
    object System :
        WanAndroidScreen("system", R.string.knowledge_system, R.drawable.ic_apps_black_24dp)

    object Project : WanAndroidScreen("project", R.string.project, R.drawable.ic_project_black_24dp)
}

@Composable
fun WanAndroidHome(
    onArticleItemClicked: OnArticleItemClicked,
    modifier: Modifier = Modifier
) {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    Scaffold(
        scaffoldState = scaffoldState,
        modifier = modifier.statusBarsPadding(),
        drawerContent = {
            WanAndroidDrawer()
        },
        topBar = {
            TopAppBar(
                title = { Text(stringResource(id = R.string.app_name)) },
                navigationIcon = {
                    Image(
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .clickable(onClick = {
                                scope.launch {
                                    scaffoldState.drawerState.open()
                                }
                            }),
                        painter = painterResource(id = R.drawable.ic_menu),
                        contentDescription = "open drawer"
                    )
                },
                actions = {
                    Image(
                        painter = painterResource(id = R.drawable.ic_search_white_24dp),
                        contentDescription = "search"
                    )
                }
            )
        },
        content = {
            WanAndroidHomeContent(onArticleItemClicked = onArticleItemClicked)
        }
    )
}

@Composable
fun WanAndroidHomeContent(
    onArticleItemClicked: OnArticleItemClicked,
    modifier: Modifier = Modifier,
) {
    val navController = rememberNavController()
    val navItems = listOf(
        WanAndroidScreen.Home,
        WanAndroidScreen.Square,
        WanAndroidScreen.Wechat,
        WanAndroidScreen.System,
        WanAndroidScreen.Project,
    )

    Scaffold(
        modifier = modifier,
        bottomBar = {
            Column(Modifier.background(ColorPrimary)) {
                BottomNavigation {
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentDestination = navBackStackEntry?.destination
                    navItems.forEach { screen ->
                        BottomNavigationItem(
                            icon = { Icon(painterResource(screen.drawableResId), null) },
                            label = { Text(stringResource(screen.stringResId)) },
                            selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                            onClick = {
                                navController.navigate(screen.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }

                            })
                    }
                }
                Spacer(modifier = modifier.navigationBarsHeight())
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {

            }) {
                Image(
                    painter = painterResource(id = R.drawable.ic_arrow_upward_white_24dp),
                    contentDescription = "floating"
                )
            }
        },
        floatingActionButtonPosition = FabPosition.End,
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = WanAndroidScreen.Home.route,
            Modifier.padding(innerPadding)
        ) {
            composable(WanAndroidScreen.Home.route) {
                WanAndroidHomeContentItem(
                    WanAndroidScreen.Home,
                    onArticleItemClicked
                )
            }
            composable(WanAndroidScreen.Square.route) {
                WanAndroidHomeContentItem(
                    WanAndroidScreen.Square,
                    onArticleItemClicked
                )
            }
            composable(WanAndroidScreen.Wechat.route) {
                WanAndroidHomeContentItem(
                    WanAndroidScreen.Wechat,
                    onArticleItemClicked
                )
            }
            composable(WanAndroidScreen.System.route) {
                WanAndroidHomeContentItem(
                    WanAndroidScreen.System,
                    onArticleItemClicked
                )
            }
            composable(WanAndroidScreen.Project.route) {
                WanAndroidHomeContentItem(
                    WanAndroidScreen.Project,
                    onArticleItemClicked
                )
            }
        }
    }
}

@Composable
fun WanAndroidHomeContentItem(
    screen: WanAndroidScreen,
    onArticleItemClicked: OnArticleItemClicked
) {
    when (screen) {
        WanAndroidScreen.Home -> {}
        WanAndroidScreen.Project -> {}
        WanAndroidScreen.Square ->{}
        WanAndroidScreen.System -> {}
        WanAndroidScreen.Wechat -> {}
    }
}
