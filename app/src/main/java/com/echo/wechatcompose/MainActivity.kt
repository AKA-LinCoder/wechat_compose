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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowCompat
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.echo.wechatcompose.ui.ChatDetailPage
import com.echo.wechatcompose.ui.ChatList
import com.echo.wechatcompose.ui.ContactList
import com.echo.wechatcompose.ui.DiscoveryList
import com.echo.wechatcompose.ui.MainPage
import com.echo.wechatcompose.ui.MeList

import com.echo.wechatcompose.ui.WeNavigationBar
import com.echo.wechatcompose.ui.theme.WeComposeTheme
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable

@Serializable object  Home

@Serializable object  ChatDetails


class MainActivity : ComponentActivity() {

    val viewModel: WeViewModel by viewModels()



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
                val navController = rememberNavController()
                NavHost(navController, Home) {
                    composable<Home>{

                        MainPage(viewModel){
                            navController.navigate(ChatDetails)
                        }
                    }
                    composable<ChatDetails> {
                       ChatDetailPage(viewModel)

                    }
                }

            }
        }
    }
}

