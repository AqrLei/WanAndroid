package com.cxz.wanandroid.compose.ui

import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import com.cxz.wanandroid.R
import com.cxz.wanandroid.compose.ui.page.home.WanAndroidHome
import com.cxz.wanandroid.compose.ui.theme.ColorPrimary
import com.cxz.wanandroid.compose.data.model.ArticleModel
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlinx.coroutines.delay

class SplashActivity : BaseComposeActivity() {


    @OptIn(ExperimentalPagerApi::class, coil.annotation.ExperimentalCoilApi::class)
    @Composable
    override fun ComposeContentView() {
        MainScreen(onArticleItemClicked = {})
    }
}

typealias OnArticleItemClicked = (article: ArticleModel) -> Unit

@ExperimentalCoilApi
@ExperimentalPagerApi
@Composable
fun MainScreen(onArticleItemClicked: OnArticleItemClicked) {
    Surface(color = ColorPrimary) {
        val transitionState = remember { MutableTransitionState(SplashState.Shown) }
        val transition = updateTransition(targetState = transitionState, label = "splashTransition")

        val splashAlpha by transition.animateFloat(
            transitionSpec = { tween(durationMillis = 2000) }, label = "splashAlpha"
        ) {
            when (it.targetState) {
                SplashState.Shown -> 0.3F
                SplashState.OnSplashed -> 1.0f
                SplashState.Completed -> 0F
            }
        }

        val contentAlpha by transition.animateFloat(
            transitionSpec = { tween(300) },
            label = "contentAlpha"
        ) {
            when (it.targetState) {
                SplashState.Shown,
                SplashState.OnSplashed -> 0F
                SplashState.Completed -> 1F
            }
        }

        Box {
            LandingScreen(
                modifier = Modifier
                    .background(alpha = splashAlpha, brush = SolidColor(ColorPrimary))
                    .alpha(splashAlpha),
                onSplash = { transitionState.targetState = SplashState.OnSplashed },
                onComplete = { transitionState.targetState = SplashState.Completed }
            )

            MainContent(
                modifier = Modifier.alpha(contentAlpha),
                onArticleItemClicked = onArticleItemClicked
            )
        }
    }
}

private const val SplashWaitTime: Long = 2000

@Composable
fun LandingScreen(modifier: Modifier = Modifier, onSplash: () -> Unit, onComplete: () -> Unit) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        val currentOnSplash by rememberUpdatedState(onSplash)
        val currentOnComplete by rememberUpdatedState(onComplete)
        LaunchedEffect(Unit) {
            currentOnSplash()
            delay(SplashWaitTime)
            currentOnComplete()
        }
        Image(
            painter = painterResource(id = R.mipmap.logo), contentDescription = "logo",
            Modifier
                .size(110.dp)
                .clip(CircleShape)
                .border(width = 2.dp, brush = SolidColor(Color.White), shape = CircleShape)
        )
    }
}

@ExperimentalCoilApi
@ExperimentalPagerApi
@Composable
private fun MainContent(
    modifier: Modifier = Modifier,
    topPadding: Dp = 0.dp,
    onArticleItemClicked: OnArticleItemClicked
) {
    Column(modifier = modifier) {
        Spacer(modifier = Modifier.padding(top = topPadding))
        WanAndroidHome(onArticleItemClicked = onArticleItemClicked, modifier = modifier)
    }
}

enum class SplashState { Shown, OnSplashed, Completed }