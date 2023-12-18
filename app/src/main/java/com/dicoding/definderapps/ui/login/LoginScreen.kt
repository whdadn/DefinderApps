package com.dicoding.definderapps.ui.login

import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dicoding.definderapps.ViewModelFactory
import com.dicoding.definderapps.data.pref.UserModel
import com.dicoding.definderapps.ui.component.validation.emailValidation
import com.dicoding.definderapps.ui.component.validation.passwordValidation
import com.dicoding.definderapps.ui.theme.DefinderAppsTheme
import com.dicoding.definderapps.utils.WindowInfo
import com.dicoding.definderapps.utils.rememberWindowInfo
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.yogi.foodlist.ui.common.UiState
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    navigateToRegister: () -> Unit,
    navigateToHome: () -> Unit,
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
                    navigateToHome = navigateToHome,
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
    navigateToHome: () -> Unit,
    viewModel:LoginViewModel,
){
    val auth: FirebaseAuth = Firebase.auth
    var emailState by rememberSaveable { mutableStateOf("") }
    var passwordState by rememberSaveable { mutableStateOf("") }

    var isErrorEmail by rememberSaveable { mutableStateOf("") }
    var isErrorPass by rememberSaveable { mutableStateOf("") }

    val windowInfo = rememberWindowInfo()
    var passwordVisibility by remember { mutableStateOf(false) }

    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
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
                        auth.signInWithEmailAndPassword(emailState, passwordState)
                            .addOnCompleteListener {
                                if (it.isSuccessful){
                                    viewModel.saveSession(UserModel(emailState))
                                    navigateToHome()
                                } else {
                                    snackBarMessage =
                                        it.exception.toString().substringAfter(":").trim()
                                    scope.launch {
                                        snackbarHostState.showSnackbar(
                                            message = snackBarMessage,
                                            withDismissAction = true,
                                            // Defaults to SnackbarDuration.Short
                                            duration = SnackbarDuration.Short,
                                        )
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
                        auth.signInWithEmailAndPassword(emailState, passwordState)
                            .addOnCompleteListener {
                                if (it.isSuccessful){
                                    viewModel.saveSession(UserModel(emailState))
                                    navigateToHome()
                                } else {
                                    snackBarMessage =
                                        it.exception.toString().substringAfter(":").trim()
                                    scope.launch {
                                        snackbarHostState.showSnackbar(
                                            message = snackBarMessage,
                                            withDismissAction = true,
                                            // Defaults to SnackbarDuration.Short
                                            duration = SnackbarDuration.Short,
                                        )
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



@Preview(
    showBackground = true,
    device = Devices.PIXEL_4_XL,
    showSystemUi = true,
)
@Composable
fun GreetingPreview() {
    DefinderAppsTheme {
        LoginScreen(navigateToRegister = {}, navigateToHome = {})
    }
}