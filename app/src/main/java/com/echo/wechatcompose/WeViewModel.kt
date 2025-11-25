package com.echo.wechatcompose


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.echo.wechatcompose.data.Chat
import com.echo.wechatcompose.data.Msg
import com.echo.wechatcompose.data.User
import com.echo.wechatcompose.ui.theme.WeComposeTheme

class WeViewModel: ViewModel() {

    var theme by mutableStateOf(WeComposeTheme.Theme.Light)
    var selectedTab by mutableIntStateOf(0)

    val contacts by mutableStateOf(
        listOf(
            User("gaolaoshi", "高老师", R.drawable.avatar_gaolaoshi),
            User("diuwuxian", "丢物线", R.drawable.avatar_diuwuxian)
        )
    )
    var chats by mutableStateOf(
        listOf(
            Chat(
                friend = User("gaolaoshi", "高老师", R.drawable.avatar_gaolaoshi),
                mutableStateListOf(
                    Msg(
                        User("gaolaoshi", "高老师", R.drawable.avatar_gaolaoshi),
                        "锄禾日当午",
                        "14:20"
                    ),
                    Msg(User.Me, "汗滴禾下土", "14:20"),
                    Msg(
                        User("gaolaoshi", "高老师", R.drawable.avatar_gaolaoshi),
                        "谁知盘中餐",
                        "14:20"
                    ),
                    Msg(User.Me, "粒粒皆辛苦", "14:20"),
                    Msg(
                        User("gaolaoshi", "高老师", R.drawable.avatar_gaolaoshi),
                        "唧唧复唧唧，木兰当户织。不闻机杼声，惟闻女叹息。",
                        "14:20"
                    ),
                    Msg(User.Me, "双兔傍地走，安能辨我是雄雌？", "14:20"),
                    Msg(
                        User("gaolaoshi", "高老师", R.drawable.avatar_gaolaoshi),
                        "床前明月光，疑是地上霜。",
                        "14:20"
                    ),
                    Msg(User.Me, "吃饭吧？", "14:20"),
                )
            ),
            Chat(
                friend = User("diuwuxian", "丢物线", R.drawable.avatar_diuwuxian),
                mutableStateListOf(
                    Msg(User("diuwuxian", "丢物线", R.drawable.avatar_diuwuxian), "哈哈哈", "13:48"),
                    Msg(User.Me, "哈哈昂", "13:48"),
                    Msg(User("diuwuxian", "丢物线", R.drawable.avatar_diuwuxian), "你笑个屁呀", "13:48").apply { read = false },
                )
            ),
        )
    )

    fun switchTheme() {
        theme = when (theme) {
            WeComposeTheme.Theme.Light -> WeComposeTheme.Theme.Dark;
            WeComposeTheme.Theme.Dark -> WeComposeTheme.Theme.NewYear
            WeComposeTheme.Theme.NewYear -> WeComposeTheme.Theme.Light
        }
    }

    fun boom(chat: Chat) {
        chat.msgs.add(Msg(User.Me, "\uD83D\uDCA3", "15:10").apply { read = true })
    }
}