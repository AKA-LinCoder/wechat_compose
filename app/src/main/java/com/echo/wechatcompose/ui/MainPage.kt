package com.echo.wechatcompose.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.echo.wechatcompose.WeViewModel
import com.echo.wechatcompose.data.Chat
import com.echo.wechatcompose.ui.theme.WeComposeTheme
import kotlinx.coroutines.launch

@Composable
fun MainPage(viewModel: WeViewModel,onOpenChat:(Chat) -> Unit){
    Column(Modifier.background(WeComposeTheme.colors.background).statusBarsPadding()) {
        val pageSate = rememberPagerState { 4 }
        HorizontalPager(pageSate, Modifier.weight(1f)) {
                page ->
            when(page) {
                0 -> ChatList(viewModel.chats){
                    onOpenChat(it)
                }
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