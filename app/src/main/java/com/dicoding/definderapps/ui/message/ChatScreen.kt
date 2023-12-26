package com.dicoding.definderapps.ui.message

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.dicoding.definderapps.R

@Composable
fun ChatScreen()
{
    Box(Modifier.fillMaxSize()) {
        Column(Modifier.align(Alignment.Center)) {
            Text(
                text = stringResource(R.string.coming_soon),
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Normal,
                    fontStyle = FontStyle.Italic
                )
            )
        }
    }
//    var messageUser by rememberSaveable { mutableStateOf("") }
//    var selectedImage by remember { mutableStateOf<List<Uri>>(emptyList()) }
//    val multiplePhotoLauncher = rememberLauncherForActivityResult(
//        contract = ActivityResultContracts.PickMultipleVisualMedia(),
//        onResult = {uris -> selectedImage = uris}
//    )
//
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .height(65.dp)
//            .padding(top = 10.dp)
//            .drawBehind {
//                val borderSize = 2.dp.toPx()
//
//                drawLine(
//                    color = Color(0xFF000080),
//                    start = Offset(0f, size.height),
//                    end = Offset(size.width, size.height),
//                    strokeWidth = borderSize
//                )
//            },
//        verticalAlignment = Alignment.CenterVertically,
//    ) {
//        Image(
//            painter = painterResource(id = R.drawable.profile_default),
//            contentDescription = "profile",
//            contentScale = ContentScale.Crop,
//            modifier = Modifier
//                .padding(start = 10.dp, end = 10.dp)
//                .size(40.dp)
//                .clip(CircleShape),
//        )
//        Column {
//            Text(
//                text = "Sukuna",
//                color = Color(0xFF000080),
//                style = MaterialTheme.typography.titleLarge.copy(
//                    fontWeight = FontWeight.Bold,
//                    fontStyle = FontStyle.Normal
//                ),
//            )
//        }
//    }
//    Column(
//        modifier = Modifier
//            .fillMaxSize(),
//        verticalArrangement = Arrangement.Bottom
//    ) {
//        TextField(
//            value = messageUser,
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(0.dp),
//            placeholder = {
//                Text(
//                    text = "Add a review comment...",
//                    style = MaterialTheme.typography.labelLarge.copy(
//                        fontWeight = FontWeight.Normal,
//                        fontStyle = FontStyle.Normal,
//                        fontSize = 16.sp
//                    ),
//                    color = Color(0xFF79747E)
//                )
//            },
//            onValueChange = {messageUser = it},
//            leadingIcon = {
//                IconButton(
//                    onClick = { multiplePhotoLauncher.launch(
//                        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
//                    ) }
//                ) {
//                    Icon(
//                        painter = painterResource(id = R.drawable.image),
//                        contentDescription = "image",
//                        tint = Color(0xFF000080)
//                    )
//                }
//            },
//            trailingIcon = {
//                IconButton(
//                    onClick = { }
//                ) {
//                    Icon(
//                        imageVector = Icons.Default.Send,
//                        contentDescription = "",
//                        tint = Color(0xFF000080)
//                    )
//                }
//            }
//        )
//    }
}

@Preview(showBackground = true)
@Composable
fun ChatScreenPreview()
{
    ChatScreen()
}