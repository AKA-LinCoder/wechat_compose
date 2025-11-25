package com.echo.wechatcompose.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.echo.wechatcompose.R
import com.echo.wechatcompose.data.Chat
import com.echo.wechatcompose.data.Msg
import com.echo.wechatcompose.data.User
import com.echo.wechatcompose.ui.theme.WeComposeTheme

@Composable
fun ChatList(chats: List<Chat>) {

    Column(Modifier.background(WeComposeTheme.colors.background).fillMaxSize()) {
        WeTopBar("Echo")
        LazyColumn(Modifier.background(WeComposeTheme.colors.listItem)) {
            itemsIndexed(chats) { index,chat ->
                if (index!=0){
                    HorizontalDivider(
                        Modifier.padding(start = 68.dp),
                        color = WeComposeTheme.colors.divider,
                        thickness = 0.8f.dp
                    )
                }
                ChatListItem(chat = chat)


            }
        }
    }

}


@Composable
private fun ChatListItem(chat: Chat, modifier: Modifier = Modifier) {
    Row(
//        modifier.fillMaxWidth()
    ) {
        Image(
            painterResource(chat.friend.avatar), chat.friend.name,
            Modifier
                .padding(8.dp)
                .size(48.dp)
                .unread(!chat.msgs.last().read, WeComposeTheme.colors.badge)
                .clip(RoundedCornerShape(4.dp))
        )
        Column(
            Modifier
                .weight(1f)
                .align(Alignment.CenterVertically)
        ) {
            Text(chat.friend.name, fontSize = 17.sp, color = WeComposeTheme.colors.textPrimary)
            Text(chat.msgs.last().text, fontSize = 14.sp, color = WeComposeTheme.colors.textSecondary)
        }
        Text(
            chat.msgs.last().time,
            Modifier.padding(8.dp, 8.dp, 12.dp, 8.dp),
            fontSize = 11.sp, color = WeComposeTheme.colors.textSecondary
        )
    }
}

fun Modifier.unread(show: Boolean,color: Color) = drawWithContent{
    drawContent()
    drawCircle(color,5.dp.toPx(), Offset(size.width-1.dp.toPx(),1.dp.toPx()))
}



@Preview
@Composable
fun ChatListPreview() {

    val chat = listOf(
        Chat(
            friend = User("gaolaoshi", "高老师", R.drawable.avatar_gaolaoshi),
            listOf(
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
            ) as MutableList<Msg>
        ),
        Chat(
            friend = User("diuwuxian", "丢物线", R.drawable.avatar_diuwuxian),
            listOf(
                Msg(User("diuwuxian", "丢物线", R.drawable.avatar_diuwuxian), "哈哈哈", "13:48"),
                Msg(User.Me, "哈哈昂", "13:48"),
                Msg(User("diuwuxian", "丢物线", R.drawable.avatar_diuwuxian), "你笑个屁呀", "13:48").apply { read = false },
            ) as MutableList<Msg>
        ),
    )
    ChatList(chats = chat)
}