package com.dicoding.definderapps.ui.login

import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dicoding.definderapps.R
import com.dicoding.definderapps.ViewModelFactory
import com.dicoding.definderapps.data.local.pref.UserModel
import com.dicoding.definderapps.ui.common.ResultState
import com.dicoding.definderapps.ui.component.validation.emailValidation
import com.dicoding.definderapps.ui.component.validation.passwordValidation
import com.dicoding.definderapps.ui.theme.DefinderAppsTheme
import com.dicoding.definderapps.utils.WindowInfo
import com.dicoding.definderapps.utils.rememberWindowInfo
import com.yogi.foodlist.ui.common.UiState
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    navigateToRegister: () -> Unit,
    navigateToWelcome:()-> Unit,
    viewModel: LoginViewModel = viewModel(
        factory= ViewModelFactory.getInstance(LocalContext.current)
    ),
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let {uiState->
        when(uiState){
            is UiState.Loading->{
                viewModel.getSession()
            }
            is UiState.Success->{
                LoginScreenContent(
                    modifier = modifier,
                    navigateToRegister = navigateToRegister,
                    navigateToWelcome = navigateToWelcome,
                    viewModel = viewModel
                )
            }
            is UiState.Error->{}
        }
    }
}

@Composable
fun LoginScreenContent(
    modifier: Modifier = Modifier,
    navigateToRegister: () -> Unit,
    navigateToWelcome: () -> Unit,
    viewModel:LoginViewModel,
    context: Context = LocalContext.current
){
    var emailState by rememberSaveable { mutableStateOf("") }
    var passwordState by rememberSaveable { mutableStateOf("") }

    var isErrorEmail by rememberSaveable { mutableStateOf("") }
    var isErrorPass by rememberSaveable { mutableStateOf("") }

    val windowInfo = rememberWindowInfo()
    var passwordVisibility by remember { mutableStateOf(false) }

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
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            if (showLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
            //potrait
            if (windowInfo.screenWidthInfo is WindowInfo.WindowType.Compact) {
                LoginPotrait(
                    modifier = modifier.padding(paddingValue),
                    email = emailState,
                    emailChange = { emailState = it },
                    pass = passwordState,
                    passChange = { passwordState = it },
                    passVisibility = passwordVisibility,
                    passVisibilityClick = { passwordVisibility = !passwordVisibility },
                    isErrorEmail = isErrorEmail,
                    isErrorPass = isErrorPass,
                    btnSignIn = {
                        isErrorEmail = emailValidation(emailState)
                        isErrorPass = passwordValidation(passwordState)
                        if (isErrorEmail == "" && isErrorPass == "") {
                            scope.launch {
                                viewModel.login(emailState.lowercase(), passwordState).asFlow().collect{
                                    when(it){
                                        is ResultState.Loading->{
                                            showLoading=true
                                        }
                                        is ResultState.Success->{
                                            val session = UserModel(
                                                id = it.data.result.id,
                                                token = it.data.token
                                            )
                                            viewModel.saveSession(session)
                                            showLoading = false
                                            navigateToWelcome()

                                        }
                                        is ResultState.Error->{
                                            snackBarMessage = context.getString(R.string.error_login)
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
                    navigateToRegister = navigateToRegister
                )

                //Landscape
            } else {
                LoginLandscape(
                    modifier = modifier.padding(paddingValue),
                    email = emailState,
                    emailChange = { emailState = it },
                    pass = passwordState,
                    passChange = { passwordState = it },
                    passVisibility = passwordVisibility,
                    passVisibilityClick = { passwordVisibility = !passwordVisibility },
                    isErrorEmail = isErrorEmail,
                    isErrorPass = isErrorPass,
                    btnSignIn = {
                        isErrorEmail = emailValidation(emailState)
                        isErrorPass = passwordValidation(passwordState)
                        if (isErrorEmail == "" && isErrorPass == "") {
                            scope.launch {
                                viewModel.login(emailState.lowercase(), passwordState).asFlow().collect{
                                    when(it){
                                        is ResultState.Loading->{
                                            showLoading=true
                                        }
                                        is ResultState.Success->{
                                            val session = UserModel(
                                                id = it.data.result.id,
                                                token = it.data.token
                                            )
                                            viewModel.saveSession(session)
                                            showLoading = false
                                            navigateToWelcome()
                                        }
                                        is ResultState.Error->{
                                            snackBarMessage = context.getString(R.string.error_login)
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
                    navigateToRegister = navigateToRegister
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
        LoginScreen(navigateToRegister = {}, navigateToWelcome = {})

    }
}