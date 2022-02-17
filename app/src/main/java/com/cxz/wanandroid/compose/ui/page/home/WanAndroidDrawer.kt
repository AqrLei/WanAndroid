package com.cxz.wanandroid.compose.ui.page.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.cxz.wanandroid.R

/**
 * created by AqrLei on 2022/2/11
 */

@Composable
fun WanAndroidDrawer(modifier: Modifier = Modifier) {
    Column(
        modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        ConstraintLayout(
            modifier = Modifier
                .background(MaterialTheme.colors.primary)
                .padding(start = 16.dp, top = 36.dp, end = 16.dp, bottom = 10.dp)
                .fillMaxWidth()
        ) {
            val (ivRank, ivAvatar, tvUserName, rowRankInfo) = createRefs()
            Image(
                painter = painterResource(id = R.drawable.ic_rank_white_24dp),
                contentDescription = null,
                modifier = Modifier
                    .size(20.dp)
                    .constrainAs(ivRank) {
                        top.linkTo(parent.top)
                        end.linkTo(parent.end)
                    }
            )

            Image(
                painter = painterResource(id = R.mipmap.ic_default_avatar),
                contentDescription = "avatar",
                modifier = Modifier
                    .constrainAs(ivAvatar) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                    }
                    .size(80.dp)
                    .clip(CircleShape)
                    .border(width = 2.dp, brush = SolidColor(Color.White), shape = CircleShape)
            )

            Text(
                text = stringResource(id = R.string.go_login),
                style = MaterialTheme.typography.body1,
                modifier = Modifier.constrainAs(tvUserName) {
                    start.linkTo(parent.start)
                    top.linkTo(ivAvatar.bottom, margin = 12.dp)
                    end.linkTo(parent.end)
                }
            )

            Row(modifier = Modifier.constrainAs(rowRankInfo) {
                start.linkTo(parent.start)
                top.linkTo(tvUserName.bottom, margin = 8.dp)
                end.linkTo(parent.end)
            }) {
                Text(
                    text = stringResource(id = R.string.nav_grade),
                    style = MaterialTheme.typography.caption
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = stringResource(id = R.string.nav_rank),
                    style = MaterialTheme.typography.caption
                )
            }

        }

        val navList = listOf(
            DrawerItem(
                stringResource(R.string.nav_my_score),
                painterResource(R.drawable.ic_score_dark_gray_24dp)
            ),
            DrawerItem(
                stringResource(R.string.nav_my_collect),
                painterResource(R.drawable.ic_share_dark_gray_24dp)
            ),
            DrawerItem(
                stringResource(R.string.my_share),
                painterResource(R.drawable.ic_todo_dark_gray_24dp)
            ),
            DrawerItem(
                stringResource(R.string.nav_todo),
                painterResource(R.drawable.ic_night_dark_gray_24dp)
            ),
            DrawerItem(
                stringResource(R.string.nav_night_mode),
                painterResource(R.drawable.ic_setting_dark_gray_24dp)
            ),
            DrawerItem(
                stringResource(R.string.nav_logout),
                painterResource(R.drawable.ic_logout_dark_gray_24dp)
            ),
        )

        for (drawerItem in navList) {
            Spacer(modifier = Modifier.height(20.dp))
            DrawerItemWidget(drawerItem = drawerItem)
        }
    }
}

@Immutable
data class DrawerItem(val name: String, val icon: Painter)

@Composable
fun DrawerItemWidget(drawerItem: DrawerItem, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 15.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(painter = drawerItem.icon, contentDescription = null)
        Spacer(Modifier.width(15.dp))
        Text(text = drawerItem.name, style = MaterialTheme.typography.body2)
    }
}