package com.cxz.wanandroid.base

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.core.view.WindowCompat
import com.cxz.wanandroid.base.ui.theme.AppTheme
import com.google.accompanist.insets.ProvideWindowInsets

/**
 * created by AqrLei on 2022/2/9
 */
abstract class BaseComposeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            ProvideWindowInsets {
                AppTheme {
                    ComposeContentView()
                }
            }
        }
    }

    @Composable
    protected abstract fun ComposeContentView()

}