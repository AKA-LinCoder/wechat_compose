package com.echo.wechatcompose

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.echo.wechatcompose.ui.ChatList

import com.echo.wechatcompose.ui.WeNavigationBar
import com.echo.wechatcompose.ui.theme.WeComposeTheme


class MainActivity : ComponentActivity() {

    // 提前保存 Context 引用
    private val mContext: MainActivity = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        WindowCompat.getInsetsController(window,window.decorView).isAppearanceLightStatusBars = false



        setContent {
            val viewModel: WeViewModel = viewModel()
            Column(Modifier.background(WeComposeTheme.colors.background).statusBarsPadding()) {
                val pageSate = rememberPagerState { 4 }
                HorizontalPager(pageSate, Modifier.weight(1f)) {
                    page ->
                        when(page) {
                            0 -> ChatList(viewModel.chats)
                            1 -> Box(Modifier.fillMaxSize())
                            2 -> Box(Modifier.fillMaxSize())
                            3 -> Box(Modifier.fillMaxSize())
                        }

                }
                WeNavigationBar(viewModel.selectedTab) {
                    viewModel.selectedTab = it
                    Toast.makeText(mContext, "gjhgjgj", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}

