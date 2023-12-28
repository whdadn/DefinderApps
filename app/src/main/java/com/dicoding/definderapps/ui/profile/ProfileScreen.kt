package com.dicoding.definderapps.ui.profile

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dicoding.definderapps.R
import com.dicoding.definderapps.ViewModelFactory
import com.dicoding.definderapps.ui.common.ResultState
import com.dicoding.definderapps.ui.mbti.MbtiScreen
import com.yogi.foodlist.ui.common.UiState

@Composable
fun ProfileScreen(
    navigateToLogin: () -> Unit,
    navigateToEditProfile: () -> Unit,
    viewModel: ProfileViewModel = viewModel(factory = ViewModelFactory.getInstance(LocalContext.current)),
    darkTheme: Boolean,
    onThemeUpdated: (Boolean) -> Unit
) {

    viewModel.session.collectAsState(initial = UiState.Loading).value.let {
        when (it) {
            is UiState.Loading -> {
                viewModel.getSession()
            }

            is UiState.Success -> {
                ProfileContent(
                    token = it.data.token,
                    userId = it.data.id,
                    mbti = it.data.mbti,
                    viewModel = viewModel,
                    darkTheme = darkTheme,
                    onThemeUpdated = onThemeUpdated,
                    navigateToLogin = navigateToLogin,
                    navigateToEditProfile = navigateToEditProfile
                )
            }

            is UiState.Error -> {
                Toast.makeText(LocalContext.current, it.errorMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileContent(
    modifier: Modifier = Modifier,
    token: String,
    userId: Int,
    mbti: String,
    context: Context = LocalContext.current,
    viewModel: ProfileViewModel,
    darkTheme: Boolean,
    onThemeUpdated: (Boolean) -> Unit,
    navigateToLogin: () -> Unit,
    navigateToEditProfile: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }
    var showLoading by rememberSaveable { mutableStateOf(false) }
    var showMbti by rememberSaveable { mutableStateOf(false) }

    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()

    Scaffold(
        modifier = Modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    titleContentColor = MaterialTheme.colorScheme.onSurface,
                ),
                title = {
                    Text(
                        text = "Profile",
                        style = MaterialTheme.typography.headlineLarge.copy(
                            fontWeight = FontWeight.Bold,
                            fontStyle = FontStyle.Normal
                        ),
                        modifier = Modifier
                            .padding(top = 5.dp),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                },
                actions = {
                    IconButton(
                        onClick = {
                            showBottomSheet = true
                        },
                    ) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = null,
                            modifier = Modifier
                                .size(65.dp),
                            tint = MaterialTheme.colorScheme.onSurface
                        )
                    }
                },
                scrollBehavior = scrollBehavior,
            )
        },
    ) { innerPadding ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            if (showLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
            viewModel.dataUser.collectAsState(initial = ResultState.Loading).value.let {
                when (it) {
                    is ResultState.Loading -> {
                        showLoading = true
                        viewModel.getUser(token, userId)
                    }

                    is ResultState.Success -> {
                        showLoading = false
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
                                Image(
                                    painter = painterResource(id = R.drawable.profile_default),
                                    contentDescription = "profile_image",
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .padding(top = 10.dp)
                                        .size(150.dp)
                                        .clip(CircleShape)
                                )
                                Text(
                                    text = it.data.data.name,
                                    color = MaterialTheme.colorScheme.onSurface,
                                    style = MaterialTheme.typography.headlineLarge.copy(
                                        fontWeight = FontWeight.Bold,
                                        fontStyle = FontStyle.Normal
                                    ),
                                    modifier = Modifier
                                        .padding(top = 8.dp)
                                        .align(Alignment.CenterHorizontally),
                                )
                                if (mbti == "") {
                                    Text(
                                        text = stringResource(id = R.string.click_here_for_set_mbti),
                                        color = MaterialTheme.colorScheme.secondary,
                                        style = MaterialTheme.typography.titleSmall.copy(
                                            fontWeight = FontWeight.Normal,
                                            fontStyle = FontStyle.Normal
                                        ),
                                        modifier = Modifier
                                            .align(Alignment.CenterHorizontally)
                                            .clickable { showMbti = true },
                                    )
                                } else {
                                    Text(
                                        text = mbti,
                                        color = MaterialTheme.colorScheme.onSurface,
                                        style = MaterialTheme.typography.titleSmall.copy(
                                            fontWeight = FontWeight.Normal,
                                            fontStyle = FontStyle.Normal
                                        ),
                                        modifier = Modifier
                                            .align(Alignment.CenterHorizontally),
                                    )
                                }
                                Divider(
                                    color = MaterialTheme.colorScheme.secondary,
                                    thickness = 2.dp,
                                    modifier = Modifier
                                        .padding(top = 6.dp),
                                )
                                viewModel.dataMbtiDesc.collectAsState(initial = ResultState.Loading).value.let { mbtiDesc ->
                                    when (mbtiDesc) {
                                        is ResultState.Loading -> {
                                            viewModel.getMbtiDesc(token)
                                        }

                                        is ResultState.Success -> {
                                            mbtiDesc.data.data.forEach { data ->
                                                if (data.name == mbti) {
                                                    Column {
                                                        Text(
                                                            text = data.description,
                                                            textAlign = TextAlign.Justify,
                                                            style = MaterialTheme.typography.bodyMedium.copy(
                                                                fontWeight = FontWeight.Normal,
                                                                fontStyle = FontStyle.Normal
                                                            ),
                                                            color = MaterialTheme.colorScheme.onSurface,
                                                            modifier = Modifier
                                                                .padding(10.dp),
                                                        )
                                                    }
                                                }
                                            }
                                        }

                                        is ResultState.Error -> {}
                                    }
                                }
                            }
                        }


                    }

                    is ResultState.Error -> {
                        showLoading = false
                        Toast.makeText(context, it.error, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    if (showBottomSheet) {
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
                        .padding(start = 16.dp, bottom = 20.dp, end = 16.dp)
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
                                tint = MaterialTheme.colorScheme.onSurface
                            )
                            Text(
                                text = stringResource(R.string.dark_mode),
                                color = MaterialTheme.colorScheme.onSurface,
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
                                .padding(end = 16.dp),
                            colors = SwitchDefaults.colors(MaterialTheme.colorScheme.primaryContainer)
                        )
                    }
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(start = 16.dp, bottom = 20.dp, end = 16.dp)
                        .fillMaxWidth()
                        .clickable {
                            showBottomSheet = false
                            navigateToEditProfile()
                        }
                ) {
                    Column(
                        modifier = Modifier.weight(2f)
                    ) {
                        Row {
                            Icon(
                                painter = painterResource(id = R.drawable.profile_filled),
                                contentDescription = stringResource(id = R.string.edit_profile),
                                modifier = Modifier
                                    .padding(start = 5.dp)
                                    .size(20.dp),
                                tint = MaterialTheme.colorScheme.onSurface
                            )
                            Text(
                                text = stringResource(R.string.edit_profile),
                                color = MaterialTheme.colorScheme.onSurface,
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
                            Icon(
                                imageVector = Icons.Default.KeyboardArrowRight,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }

                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(start = 16.dp, bottom = 20.dp, end = 16.dp)
                        .fillMaxWidth()
                        .clickable {
                            Toast
                                .makeText(
                                    context,
                                    context.getString(R.string.coming_soon),
                                    Toast.LENGTH_SHORT
                                )
                                .show()
                        }
                ) {
                    Column(
                        modifier = Modifier.weight(2f)
                    ) {
                        Row {
                            Icon(
                                painter = painterResource(id = R.drawable.tour_guide),
                                contentDescription = stringResource(id = R.string.tour_guide),
                                modifier = Modifier
                                    .padding(start = 5.dp)
                                    .size(20.dp),
                                tint = MaterialTheme.colorScheme.onSurface
                            )
                            Text(
                                text = stringResource(R.string.tour_guide),
                                color = MaterialTheme.colorScheme.onSurface,
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
                            Icon(
                                imageVector = Icons.Default.KeyboardArrowRight,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }

                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(start = 16.dp, bottom = 20.dp, end = 16.dp)
                        .fillMaxWidth()
                        .clickable {
                            Toast
                                .makeText(
                                    context,
                                    context.getString(R.string.coming_soon),
                                    Toast.LENGTH_SHORT
                                )
                                .show()
                        }
                ) {
                    Column(
                        modifier = Modifier.weight(2f)
                    ) {
                        Row {
                            Icon(
                                painter = painterResource(id = R.drawable.mail),
                                contentDescription = stringResource(id = R.string.message),
                                modifier = Modifier
                                    .padding(start = 5.dp),
                                tint = MaterialTheme.colorScheme.onSurface
                            )
                            Text(
                                text = stringResource(R.string.message),
                                color = MaterialTheme.colorScheme.onSurface,
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
                            Icon(
                                imageVector = Icons.Default.KeyboardArrowRight,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onSurface
                            )
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
                        modifier = Modifier.weight(2f)
                    ) {
                        Row {
                            Icon(
                                painter = painterResource(id = R.drawable.logout),
                                contentDescription = stringResource(id = R.string.log_out),
                                modifier = Modifier
                                    .padding(start = 5.dp),
                                tint = MaterialTheme.colorScheme.onSurface
                            )
                            Text(
                                text = stringResource(R.string.log_out),
                                color = MaterialTheme.colorScheme.onSurface,
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
                            Icon(
                                imageVector = Icons.Default.KeyboardArrowRight,
                                contentDescription = null
                            )
                        }
                    }

                }
            }
            Spacer(modifier = Modifier.height(50.dp))
        }
    } else if (showMbti) {
        MbtiScreen(closeDialog = { showMbti = false })
    }
    LaunchedEffect(showMbti) {
        if (!showMbti) {
            viewModel.getUser(token, userId)
        }
    }
    LaunchedEffect(showBottomSheet)
    {
        if (!showBottomSheet) {
            viewModel.getUser(token, userId)
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
    ProfileScreen(
        navigateToLogin = {},
        darkTheme = false,
        onThemeUpdated = {},
        navigateToEditProfile = {})
}