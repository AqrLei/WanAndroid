package com.cxz.wanandroid.ui.activity

import android.content.Intent
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.constraintlayout.compose.ConstraintLayout
import com.cxz.wanandroid.base.BaseComposeActivity
//import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : BaseComposeActivity() {

    private var alphaAnimation: AlphaAnimation? = null

//    override fun attachLayoutRes(): Int = R.layout.activity_splash

    override fun useEventBus(): Boolean = false

    override fun enableNetworkTip(): Boolean = false

    override fun initData() {
    }

    @Composable
    override fun ComposeContentView() {
        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            Text("HelloWorld", Modifier.constrainAs(createRef()) {
                start.linkTo(parent.start)
                top.linkTo(parent.top)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
            })
        }
    }

    override fun initView() {
        alphaAnimation = AlphaAnimation(0.3F, 1.0F)
        alphaAnimation?.run {
            duration = 2000
            setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationRepeat(p0: Animation?) {
                }

                override fun onAnimationEnd(p0: Animation?) {
//                    jumpToMain()
                }

                override fun onAnimationStart(p0: Animation?) {
                }
            })
        }
//        layout_splash.startAnimation(alphaAnimation)
    }

    override fun initColor() {
        super.initColor()
//        layout_splash.setBackgroundColor(mThemeColor)
    }

    override fun start() {
    }

    fun jumpToMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }

}
