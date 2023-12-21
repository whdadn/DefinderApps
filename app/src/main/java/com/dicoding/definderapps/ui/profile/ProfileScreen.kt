package com.dicoding.definderapps.ui.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dicoding.definderapps.R
import com.dicoding.definderapps.ViewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    modifier:Modifier = Modifier,
    navigateToLogin:()->Unit,
    viewModel: ProfileViewModel = viewModel(factory= ViewModelFactory.getInstance(LocalContext.current)),
    darkTheme: Boolean, onThemeUpdated: (Boolean) -> Unit
) {
    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .padding(top = 16.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            IconButton(
                onClick = {
                    showBottomSheet = true
                },
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(end = 7.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = null,
                    modifier = Modifier
                        .size(65.dp),
                    tint = Color(0xFF000080)
                )
            }
            Image(
                painter = painterResource(id = R.drawable.pantai_pandawa),
                contentDescription = "profile_image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(top = 30.dp)
                    .size(200.dp)
                    .clip(CircleShape)
            )
            Text(
                text = "Shoratorizawa",
                color = Color(0xFF000080),
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Normal
                ),
                modifier = Modifier
                    .padding(top = 8.dp)
                    .align(Alignment.CenterHorizontally),
            )
            Text(
                text = "MBTI / Tour Guide",
                color = Color(0xFF000080),
                style = MaterialTheme.typography.titleSmall.copy(
                    fontWeight = FontWeight.Normal,
                    fontStyle = FontStyle.Normal
                ),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
            )
            Divider(
                color = Color(0xFF000080),
                thickness = 2.dp,
                modifier = Modifier
                    .padding(top = 6.dp),
            )
            LazyVerticalGrid(
                columns = GridCells.Fixed(2)
            ) {
                items(10)
                {
                    Box(
                        modifier = Modifier
                            .wrapContentSize()
                    ) {
                        Card(
                            modifier = Modifier
                                .wrapContentSize()
                                .padding(top = 10.dp, start = 8.dp, end = 8.dp, bottom = 10.dp),
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.pantai_pandawa),
                                contentDescription = "image",
                                modifier = Modifier
                                    .wrapContentSize()
                            )
                        }
                    }
                }
            }
        }
    }
    if (showBottomSheet){
        ModalBottomSheet(
            onDismissRequest = {
                showBottomSheet = false
            },
            sheetState = sheetState,
            modifier = Modifier
                .navigationBarsPadding()
        ) {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(start = 16.dp, bottom = 20.dp, end=16.dp)
                ) {
                    Column(
                        Modifier.weight(2f)
                    ) {
                        Row {
                            Icon(
                                painter = painterResource(id = R.drawable.dark_mode_black_24dp),
                                contentDescription = "dark_mode",
                                modifier = Modifier
                                    .padding(start = 5.dp),
                                tint = Color(0xFF000080)
                            )
                            Text(
                                text = stringResource(R.string.dark_mode),
                                color = Color(0xFF000080),
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.Normal,
                                    fontStyle = FontStyle.Normal
                                ),
                                modifier = Modifier
                                    .padding(start = 5.dp, bottom = 3.dp)
                            )
                        }
                    }
                    Column(
                        horizontalAlignment = Alignment.End,
                        modifier = Modifier.weight(0.5f)
                    ) {
                        Switch(
                            checked = darkTheme,
                            onCheckedChange = onThemeUpdated,
                            modifier = Modifier
                                .size(32.dp)
                                .padding(end = 16.dp)
                        )
                    }

                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(start = 16.dp, bottom = 20.dp, end = 16.dp)
                        .fillMaxWidth()
                        .clickable {  }
                ) {
                    Column(
                        modifier= Modifier.weight(2f)
                    ) {
                        Row {
                            Icon(
                                painter = painterResource(id = R.drawable.tour_guide),
                                contentDescription = "Log Out",
                                modifier = Modifier
                                    .padding(start = 5.dp)
                                    .size(20.dp),
                                tint = Color(0xFF000080)
                            )
                            Text(
                                text = "Tour Guide",
                                color = Color(0xFF000080),
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.Normal,
                                    fontStyle = FontStyle.Normal
                                ),
                                modifier = Modifier
                                    .padding(start = 5.dp, bottom = 3.dp)
                            )
                        }
                    }
                    Column(
                        horizontalAlignment = Alignment.End,
                        modifier = Modifier.weight(0.5f)
                    ) {
                        Row {
                            Icon(imageVector = Icons.Default.KeyboardArrowRight,
                                contentDescription = null)
                        }
                    }

                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(start = 16.dp, bottom = 20.dp, end = 16.dp)
                        .fillMaxWidth()
                        .clickable {  }
                ) {
                    Column(
                        modifier= Modifier.weight(2f)
                    ) {
                        Row {
                            Icon(
                                painter = painterResource(id = R.drawable.mail),
                                contentDescription = "Message",
                                modifier = Modifier
                                    .padding(start = 5.dp),
                                tint = Color(0xFF000080)
                            )
                            Text(
                                text = "Message",
                                color = Color(0xFF000080),
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.Normal,
                                    fontStyle = FontStyle.Normal
                                ),
                                modifier = Modifier
                                    .padding(start = 5.dp, bottom = 3.dp)
                            )
                        }
                    }
                    Column(
                        horizontalAlignment = Alignment.End,
                        modifier = Modifier.weight(0.5f)
                    ) {
                        Row {
                            Icon(imageVector = Icons.Default.KeyboardArrowRight,
                                contentDescription = null)
                        }
                    }

                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(start = 16.dp, bottom = 20.dp, end = 16.dp)
                        .fillMaxWidth()
                        .clickable {  }
                ) {
                    Column(
                        modifier= Modifier.weight(2f)
                    ) {
                        Row {
                            Icon(
                                painter = painterResource(id = R.drawable.edit_icon),
                                contentDescription = "edit_profile",
                                modifier = Modifier
                                    .padding(start = 5.dp),
                                tint = Color(0xFF000080)
                            )
                            Text(
                                text = "Edit Profile",
                                color = Color(0xFF000080),
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.Normal,
                                    fontStyle = FontStyle.Normal
                                ),
                                modifier = Modifier
                                    .padding(start = 5.dp, bottom = 3.dp)
                            )
                        }
                    }
                    Column(
                        horizontalAlignment = Alignment.End,
                        modifier = Modifier.weight(0.5f)
                    ) {
                        Row {
                            Icon(imageVector = Icons.Default.KeyboardArrowRight,
                                contentDescription = null)
                        }
                    }

                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(start = 16.dp, bottom = 20.dp, end = 16.dp)
                        .fillMaxWidth()
                        .clickable {
                            if (viewModel.logout()) {
                                navigateToLogin()
                            }
                        }
                ) {
                    Column(
                        modifier= Modifier.weight(2f)
                    ) {
                        Row {
                            Icon(
                                painter = painterResource(id = R.drawable.logout),
                                contentDescription = "Log Out",
                                modifier = Modifier
                                    .padding(start = 5.dp),
                                tint = Color(0xFF000080)
                            )
                            Text(
                                text = "Logout",
                                color = Color(0xFF000080),
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.Normal,
                                    fontStyle = FontStyle.Normal
                                ),
                                modifier = Modifier
                                    .padding(start = 5.dp, bottom = 3.dp)
                            )
                        }
                    }
                    Column(
                        horizontalAlignment = Alignment.End,
                        modifier = Modifier.weight(0.5f)
                    ) {
                        Row {
                            Icon(imageVector = Icons.Default.KeyboardArrowRight,
                                contentDescription = null)
                        }
                    }

                }
            }
            Spacer(modifier = Modifier.height(50.dp))
        }
    }
}

@Preview(
    showSystemUi = true,
    showBackground = true,
    device = Devices.PIXEL_4_XL
)
@Composable
fun ProfileItemPreview() {
    ProfileScreen( navigateToLogin = {}, darkTheme = false, onThemeUpdated = {})
}