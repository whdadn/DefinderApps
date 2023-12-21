package com.dicoding.definderapps.ui.register

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dicoding.definderapps.R
import com.dicoding.definderapps.ViewModelFactory
import com.dicoding.definderapps.ui.common.ResultState
import com.dicoding.definderapps.ui.component.validation.emailValidation
import com.dicoding.definderapps.ui.component.validation.nameValidation
import com.dicoding.definderapps.ui.component.validation.passwordValidation
import com.dicoding.definderapps.ui.theme.DefinderAppsTheme
import com.dicoding.definderapps.utils.WindowInfo
import com.dicoding.definderapps.utils.rememberWindowInfo
import kotlinx.coroutines.launch

@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,
    navigateToLogin: () -> Unit,
    context: Context = LocalContext.current,
    viewModel: RegisterViewModel = viewModel(factory = ViewModelFactory.getInstance(context))
) {
    var emailState by rememberSaveable { mutableStateOf("") }
    var nameState by rememberSaveable { mutableStateOf("") }
    var passwordState by rememberSaveable { mutableStateOf("") }

    var isErrorEmail by rememberSaveable { mutableStateOf("") }
    var isErrorName by rememberSaveable { mutableStateOf("") }
    var isErrorPass by rememberSaveable { mutableStateOf("") }

    val windowInfo = rememberWindowInfo()
    var passwordVisibility by rememberSaveable { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    var showLoading by rememberSaveable { mutableStateOf(false) }
    var snackBarMessage by rememberSaveable { mutableStateOf("") }


    Scaffold(
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState,
                modifier = Modifier.padding(bottom = 4.dp)
            )
        }
    ) { paddingValue ->
        //potrait
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            if (showLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Center))
            }
            if (windowInfo.screenWidthInfo is WindowInfo.WindowType.Compact) {
                RegisterPotrait(
                    modifier = modifier.padding(paddingValue),
                    email = emailState,
                    emailChange = { emailState = it },
                    name = nameState,
                    nameChange = { nameState = it },
                    pass = passwordState,
                    passChange = { passwordState = it },
                    passVisibility = passwordVisibility,
                    passVisibilityClick = { passwordVisibility = !passwordVisibility },
                    isErrorEmail = isErrorEmail,
                    isErrorName = isErrorName,
                    isErrorPass = isErrorPass,
                    btnSignUp = {
                        isErrorEmail = emailValidation(emailState)
                        isErrorName = nameValidation(nameState)
                        isErrorPass = passwordValidation(passwordState)
                        if (isErrorEmail == "" && isErrorName == "" && isErrorPass == "") {
                            scope.launch {
                                viewModel.register(nameState, emailState.lowercase(), passwordState, passwordState).asFlow()
                                    .collect {
                                        when (it) {
                                            is ResultState.Loading -> {
                                                showLoading = true
                                            }

                                            is ResultState.Success -> {
                                                snackBarMessage = context.getString(R.string.success_created_account)
                                                showLoading = false
                                                val result = snackbarHostState.showSnackbar(
                                                    message = snackBarMessage,
                                                    withDismissAction = true,
                                                    duration = SnackbarDuration.Short,
                                                    actionLabel = context.getString(R.string.login)
                                                )
                                                when (result) {
                                                    SnackbarResult.ActionPerformed -> {
                                                        navigateToLogin()
                                                    }
                                                    else -> {}
                                                }

                                            }

                                            is ResultState.Error -> {
                                                snackBarMessage = it.error
                                                showLoading = false
                                                snackbarHostState.showSnackbar(
                                                    message = snackBarMessage,
                                                    withDismissAction = true,
                                                    duration = SnackbarDuration.Short,
                                                )
                                            }
                                        }
                                    }
                            }
                        }
                    },
                    navigateToLogin = navigateToLogin
                )
                //Landscape
            } else {
                RegisterLandscape(
                    modifier = modifier.padding(paddingValue),
                    email = emailState,
                    emailChange = { emailState = it },
                    name = nameState,
                    nameChange = { nameState = it },
                    pass = passwordState,
                    passChange = { passwordState = it },
                    passVisibility = passwordVisibility,
                    passVisibilityClick = { passwordVisibility = !passwordVisibility },
                    isErrorEmail = isErrorEmail,
                    isErrorName = isErrorName,
                    isErrorPass = isErrorPass,
                    btnSignUp = {
                        isErrorEmail = emailValidation(emailState)
                        isErrorName = nameValidation(nameState)
                        isErrorPass = passwordValidation(passwordState)
                        if (isErrorEmail == "" && isErrorName == "" && isErrorPass == "") {
                            scope.launch {
                                viewModel.register(nameState, emailState.lowercase(), passwordState, passwordState).asFlow()
                                    .collect {
                                        when (it) {
                                            is ResultState.Loading -> {
                                                showLoading = true
                                            }

                                            is ResultState.Success -> {
                                                snackBarMessage = context.getString(R.string.success_created_account)
                                                showLoading = false
                                                val result = snackbarHostState.showSnackbar(
                                                    message = snackBarMessage,
                                                    withDismissAction = true,
                                                    duration = SnackbarDuration.Short,
                                                    actionLabel = context.getString(R.string.login)
                                                )
                                                when (result) {
                                                    SnackbarResult.ActionPerformed -> {
                                                        navigateToLogin()
                                                    }
                                                    else -> {}
                                                }

                                            }

                                            is ResultState.Error -> {
                                                snackBarMessage = it.error
                                                showLoading = false
                                                snackbarHostState.showSnackbar(
                                                    message = snackBarMessage,
                                                    withDismissAction = true,
                                                    duration = SnackbarDuration.Short,
                                                )
                                            }
                                        }
                                    }
                            }
                        }
                    },
                    navigateToLogin = navigateToLogin
                )
            }
        }
    }

}

@Preview(
    showBackground = true,
    device = Devices.PIXEL_4_XL,
    showSystemUi = true,
)
@Composable
fun GreetingPreview() {
    DefinderAppsTheme {
        RegisterScreen(navigateToLogin = {})
    }
}