package com.cxz.wanandroid.ui.activity

import android.content.Intent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.cxz.wanandroid.R
import com.cxz.wanandroid.base.BaseComposeActivity
import com.cxz.wanandroid.base.ui.theme.ColorPrimary

class SplashActivity : BaseComposeActivity() {

    @Composable
    override fun ComposeContentView() {

        var alphaState by remember { mutableStateOf(false) }

        val alpha by animateFloatAsState(
            targetValue = if (alphaState) 1F else 0.3F,
            animationSpec = tween(durationMillis = 2000),
            finishedListener = { jumpToMain() })

        LaunchedEffect(null) {
            alphaState = true
        }

        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .background(alpha = alpha, brush = SolidColor(ColorPrimary))
        ) {
            Image(
                painter = painterResource(id = R.mipmap.logo), contentDescription = "logo",
                Modifier
                    .constrainAs(createRef()) {
                        width = Dimension.value(110.dp)
                        height = Dimension.value(110.dp)
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                    }
                    .border(width = 2.dp, brush = SolidColor(Color.White), shape = CircleShape)
            )
        }
    }

    private fun jumpToMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }
}
