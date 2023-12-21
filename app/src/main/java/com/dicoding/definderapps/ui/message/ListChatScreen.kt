package com.dicoding.definderapps.ui.message

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dicoding.definderapps.R
import com.dicoding.definderapps.ui.component.messageitem.ChatItem

data class Chat(
    val id: Int,
    val image: Int,
    val name: String,
    val message: String,
    val time: String
)

val dataChat = listOf(
    Chat(
        1,
        R.drawable.profile_default,
        "Sukuna",
        "Lorem ipsum",
        "01:06 AM"
    ),
    Chat(
        2,
        R.drawable.profile_default,
        "Geto",
        "Lorem ipsum",
        "02:06 AM"
    ),
    Chat(
        3,
        R.drawable.profile_default,
        "Itadori",
        "Lorem ipsum",
        "03:06 AM"
    ),
    Chat(
        4,
        R.drawable.profile_default,
        "Yuta",
        "Lorem ipsum",
        "04:06 AM"
    ),
    Chat(
        5,
        R.drawable.profile_default,
        "Tok Aba",
        "Lorem ipsum",
        "05:06 AM"
    ),
    Chat(
        6,
        R.drawable.profile_default,
        "Adududu",
        "Lorem ipsum",
        "06:06 AM"
    ),
    Chat(
        7,
        R.drawable.profile_default,
        "Retakka",
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent ullamcorper metus odio, nec finibus tortor tincidunt at. Morbi tempor pulvinar.",
        "07:06 AM"
    ),
    Chat(
        8,
        R.drawable.profile_default,
        "Glacier",
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent ullamcorper metus odio, nec finibus tortor tincidunt at. Morbi tempor pulvinar.",
        "08:06 AM"
    )
)
@Composable
fun ListChatScreen()
{
    LazyColumn(
        contentPadding = PaddingValues(16.dp)
    ){
        items(dataChat){list ->
            ChatItem(
                image = list.image,
                name = list.name,
                message = list.message,
                time = list.time
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ListChatScreenPreview()
{
    ListChatScreen()
}