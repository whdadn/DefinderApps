package com.dicoding.definderapps.ui.mbti

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dicoding.definderapps.R
import com.dicoding.definderapps.ViewModelFactory
import com.dicoding.definderapps.data.local.pref.UserModel
import com.dicoding.definderapps.ui.common.ResultState
import com.dicoding.definderapps.ui.profile.ProfileContent
import com.dicoding.definderapps.ui.welcome.WelcomeViewModel
import com.yogi.foodlist.ui.common.UiState
import kotlinx.coroutines.launch


@Composable
fun MbtiScreen(
    closeDialog: () -> Unit,
    viewModel: WelcomeViewModel = viewModel(factory = ViewModelFactory.getInstance(LocalContext.current))
) {
    viewModel.mbti.collectAsState(initial = UiState.Loading).value.let {
        when (it) {
            is UiState.Loading -> {
                viewModel.yourMbti()
            }

            is UiState.Success -> {
                MbtiContent(
                    mbti = it.data,
                    viewModel = viewModel,
                    closeDialog = closeDialog
                )
            }

            is UiState.Error -> {
                Toast.makeText(LocalContext.current, it.errorMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }


}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MbtiContent(
    mbti: String,
    context: Context = LocalContext.current,
    viewModel: WelcomeViewModel,
    closeDialog: () -> Unit
) {
    var inputMbti by rememberSaveable { mutableStateOf("") }

    Dialog(
        onDismissRequest = {
            closeDialog()
        },
        properties = DialogProperties(
            usePlatformDefaultWidth = false // experimental
        )
    ) {
        val scope = rememberCoroutineScope()
        val snackbarHostState = remember { SnackbarHostState() }
        var showLoading by rememberSaveable { mutableStateOf(false) }

        Scaffold(
            snackbarHost = {
                SnackbarHost(
                    hostState = snackbarHostState,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
            }
        ) {
            Surface(modifier = Modifier.fillMaxSize()) {
                Box(modifier = Modifier.fillMaxSize()) {
                    if (showLoading) {
                        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                    }
                    LazyColumn(
                        modifier = Modifier
                            .padding(16.dp)
                    ) {
                        item {
                            Row() {
                                Text(
                                    text = stringResource(R.string.info_mbti),
                                    color = MaterialTheme.colorScheme.onSurface,
                                    style = MaterialTheme.typography.headlineMedium.copy(
                                        fontWeight = FontWeight.Bold,
                                        fontStyle = FontStyle.Normal
                                    ),
                                    modifier = Modifier
                                        .weight(2f)
                                        .padding(top = 40.dp)
                                )
                                IconButton(onClick = closeDialog) {
                                    Icon(
                                        imageVector = Icons.Default.Close,
                                        contentDescription = null
                                    )
                                }
                            }
                            Text(
                                text = stringResource(R.string.mbti_form),
                                color = MaterialTheme.colorScheme.onSurface,
                                style = MaterialTheme.typography.titleSmall,
                                modifier = Modifier
                                    .padding(top = 17.dp)
                            )
                            OutlinedTextField(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                value = inputMbti,
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                                    unfocusedBorderColor = MaterialTheme.colorScheme.outline,
                                ),
                                textStyle = TextStyle.Default.copy(
                                    fontSize = 16.sp,
                                    textAlign = TextAlign.Justify
                                ),
                                onValueChange = { inputMbti = it },
                            )

                            Button(
                                onClick = {
                                    if (inputMbti != "") {
                                        scope.launch {
                                            viewModel.getMbti(inputMbti).asFlow().collect {
                                                when (it) {
                                                    is ResultState.Loading -> {
                                                        showLoading = true
                                                    }

                                                    is ResultState.Success -> {
                                                        viewModel.saveMbti(it.data.mbti)
                                                        showLoading = false
                                                        val result = snackbarHostState.showSnackbar(
                                                            message = context.getString(R.string.your_mbti_has_been_saved),
                                                            withDismissAction = true,
                                                            duration = SnackbarDuration.Short,
                                                            actionLabel = context.getString(R.string.close)
                                                        )
                                                        when (result) {
                                                            SnackbarResult.ActionPerformed -> {
                                                                closeDialog()
                                                            }
                                                            else -> {}
                                                        }
                                                    }

                                                    is ResultState.Error -> {
                                                        showLoading = false
                                                        snackbarHostState.showSnackbar(
                                                            message = context.getString(R.string.failed_to_get_your_mbti),
                                                            withDismissAction = false,
                                                            duration = SnackbarDuration.Short,
                                                        )
                                                    }
                                                }
                                            }
                                        }
                                    } else {
                                        scope.launch {
                                            snackbarHostState.showSnackbar(
                                                message = context.getString(R.string.error_field_empty),
                                                withDismissAction = false,
                                                duration = SnackbarDuration.Short,
                                            )
                                        }
                                    }
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 17.dp),
                                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
                                shape = RoundedCornerShape(4.dp)
                            ) {
                                Text(
                                    modifier = Modifier.padding(vertical = 4.dp),
                                    text = stringResource(id = R.string.submit),
                                    color = MaterialTheme.colorScheme.onPrimary,
                                    style = MaterialTheme.typography.bodyLarge.copy(
                                        fontWeight = FontWeight.Normal,
                                        fontStyle = FontStyle.Normal
                                    )
                                )
                            }
                            Divider(
                                thickness = 2.dp,
                                modifier = Modifier
                                    .padding(top = 15.dp),
                                color = MaterialTheme.colorScheme.secondary
                            )
                           Column(
                               modifier = Modifier
                                   .fillMaxWidth()
                           ) {
                               Text(
                                   text = "Your personality type is:",
                                   modifier = Modifier
                                       .padding(top = 10.dp)
                                       .align(Alignment.CenterHorizontally),
                                   style = MaterialTheme.typography.titleSmall.copy(
                                       fontWeight = FontWeight.Normal,
                                       fontStyle = FontStyle.Normal
                                   ),
                                   color = MaterialTheme.colorScheme.onSurface,
                               )
                               Text(
                                   text = mbti,
                                   modifier = Modifier
                                       .align(Alignment.CenterHorizontally)
                                       .padding(top = 10.dp),
                                   style = MaterialTheme.typography.titleLarge.copy(
                                       fontWeight = FontWeight.Bold,
                                       fontStyle = FontStyle.Normal
                                   ),
                                   color = MaterialTheme.colorScheme.onSurface,
                               )
                           }
                        }
                    }
                }
            }
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
fun MbtiScreenPreview() {
    MbtiScreen(closeDialog = { })
}