package com.dicoding.definderapps.ui.message

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dicoding.definderapps.R

@Composable
fun ChatScreen()
{
    var messageUser by rememberSaveable { mutableStateOf("") }
    var selectedImage by remember { mutableStateOf<List<Uri>>(emptyList()) }
    val multiplePhotoLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickMultipleVisualMedia(),
        onResult = {uris -> selectedImage = uris}
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(65.dp)
            .padding(top = 10.dp)
            .drawBehind {
                val borderSize = 2.dp.toPx()

                drawLine(
                    color = Color(0xFF000080),
                    start = Offset(0f, size.height),
                    end = Offset(size.width, size.height),
                    strokeWidth = borderSize
                )
            },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painter = painterResource(id = R.drawable.profile_default),
            contentDescription = "profile",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(start = 10.dp, end = 10.dp)
                .size(40.dp)
                .clip(CircleShape),
        )
        Column {
            Text(
                text = "Sukuna",
                color = Color(0xFF000080),
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Normal
                ),
            )
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Bottom
    ) {
        TextField(
            value = messageUser,
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp),
            placeholder = {
                Text(
                    text = "Add a review comment...",
                    style = MaterialTheme.typography.labelLarge.copy(
                        fontWeight = FontWeight.Normal,
                        fontStyle = FontStyle.Normal,
                        fontSize = 16.sp
                    ),
                    color = Color(0xFF79747E)
                )
            },
            onValueChange = {messageUser = it},
            leadingIcon = {
                IconButton(
                    onClick = { multiplePhotoLauncher.launch(
                        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                    ) }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.image),
                        contentDescription = "image",
                        tint = Color(0xFF000080)
                    )
                }
            },
            trailingIcon = {
                IconButton(
                    onClick = { /*TODO*/ }
                ) {
                    Icon(
                        imageVector = Icons.Default.Send,
                        contentDescription = "",
                        tint = Color(0xFF000080)
                    )
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ChatScreenPreview()
{
    ChatScreen()
}