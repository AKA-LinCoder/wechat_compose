package com.echo.wechatcompose


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class WeViewModel: ViewModel() {
    var selectedTab by mutableIntStateOf(0)
}