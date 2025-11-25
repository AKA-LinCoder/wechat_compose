package com.echo.wechatcompose

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.echo.wechatcompose.ui.ChatList
import com.echo.wechatcompose.ui.ContactList
import com.echo.wechatcompose.ui.DiscoveryList
import com.echo.wechatcompose.ui.MeList

import com.echo.wechatcompose.ui.WeNavigationBar
import com.echo.wechatcompose.ui.theme.WeComposeTheme
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {

    val viewModel: WeViewModel by viewModels()

    // 提前保存 Context 引用
    private val mContext: MainActivity = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val insetsController =  WindowCompat.getInsetsController(window,window.decorView)

        //启动协程
        lifecycleScope.launch {
            viewModel.isLightThemeFlow.collect {
                insetsController.isAppearanceLightStatusBars = it
            }
        }



        setContent {
//            val viewModel: WeViewModel = viewModel()
            WeComposeTheme(viewModel.theme) {
                Column(Modifier.background(WeComposeTheme.colors.background).statusBarsPadding()) {
                    val pageSate = rememberPagerState { 4 }
                    HorizontalPager(pageSate, Modifier.weight(1f)) {
                            page ->
                        when(page) {
                            0 -> ChatList(viewModel.chats)
                            1 -> ContactList(viewModel.contacts)
                            2 -> DiscoveryList()
                            3 -> MeList()
                        }

                    }
                    val scope = rememberCoroutineScope ()
                    WeNavigationBar(pageSate.currentPage) {
                       scope.launch {
                           pageSate.scrollToPage(it)
                       }
                    }
                }
            }
        }
    }
}

