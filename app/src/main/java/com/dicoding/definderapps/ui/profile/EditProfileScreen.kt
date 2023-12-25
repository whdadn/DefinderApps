package com.dicoding.definderapps.ui.profile

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dicoding.definderapps.R
import com.dicoding.definderapps.ViewModelFactory
import com.dicoding.definderapps.ui.common.ResultState
import com.dicoding.definderapps.ui.profile.edit.EditAccountUser
import com.dicoding.definderapps.ui.profile.edit.EditPasswordUser
import com.yogi.foodlist.ui.common.UiState

@Composable
fun EditProfileScreen(
    closeScreen: () -> Unit,
    viewModel: ProfileViewModel = viewModel(factory = ViewModelFactory.getInstance(LocalContext.current))
) {
    viewModel.session.collectAsState(initial = UiState.Loading).value.let {
        when (it) {
            is UiState.Loading -> {
                viewModel.getSession()
            }

            is UiState.Success -> {
                EditProfileContent(
                    token = it.data.token,
                    userId = it.data.id,
                    closeScreen = closeScreen,
                    viewModel = viewModel
                )
            }

            is UiState.Error -> {
                Toast.makeText(LocalContext.current, it.errorMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }
}

@Composable
fun EditProfileContent(
    token: String,
    userId: Int,
    closeScreen: () -> Unit,
    viewModel: ProfileViewModel
) {
    var showEditAccount by rememberSaveable { mutableStateOf(false) }
    var showEditPassword by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Row() {
            Text(
                text = stringResource(R.string.edit_profile),
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Normal
                ),
                modifier = Modifier
                    .weight(2f)
                    .padding(top = 40.dp)
            )
            IconButton(onClick = closeScreen) {
                Icon(imageVector = Icons.Default.Close, contentDescription = null)
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(bottom = 20.dp, top = 40.dp)
                .fillMaxWidth()
                .clickable {
                    showEditAccount = true
                }
        ) {
            Column(
                modifier = Modifier.weight(2f)
            ) {
                Row {
                    Text(
                        text = stringResource(id = R.string.edit_account),
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
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(bottom = 20.dp)
                .fillMaxWidth()
                .clickable {
                    showEditPassword = true
                }
        ) {
            Column(
                modifier = Modifier.weight(2f)
            ) {
                Row {
                    Text(
                        text = stringResource(id = R.string.change_password),
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
    if (showEditAccount) {
        viewModel.dataUser.collectAsState(initial = ResultState.Loading).value.let {
            when (it) {
                is ResultState.Loading -> {
                    viewModel.getUser(token, userId)
                }

                is ResultState.Success -> {
                    EditAccountUser(
                        token = token,
                        userId = userId,
                        name = it.data.data.name,
                        email = it.data.data.email,
                        closeDialog = { showEditAccount = false })
                }

                is ResultState.Error -> {
                    Toast.makeText(LocalContext.current, it.error, Toast.LENGTH_SHORT).show()
                }
            }
        }
    } else if (showEditPassword) {
        EditPasswordUser(
            token = token,
            userId = userId,
            closeDialog = { showEditPassword = false })
    }
    LaunchedEffect(showEditAccount)
    {
        if (!showEditAccount)
        {
            viewModel.getUser(token, userId)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EditProfileScreenPreview() {
    EditProfileScreen(closeScreen = {})
}