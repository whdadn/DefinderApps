package com.dicoding.definderapps.ui.profile.edit

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dicoding.definderapps.R
import com.dicoding.definderapps.ViewModelFactory
import com.dicoding.definderapps.ui.common.ResultState
import com.dicoding.definderapps.ui.component.validation.ErrorPasswordMessage
import com.dicoding.definderapps.ui.component.validation.passwordValidation
import com.dicoding.definderapps.ui.profile.ProfileViewModel
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun EditPasswordUser(
    token:String,
    userId:Int,
    closeDialog:()->Unit,
    context: Context = LocalContext.current,
    viewModel: ProfileViewModel = viewModel(factory=ViewModelFactory.getInstance(context))
){
    var inputNewPassword by rememberSaveable { mutableStateOf("") }
    var inputOldPassword by rememberSaveable { mutableStateOf("") }
    var showLoading by rememberSaveable{ mutableStateOf(false) }
    var showPasswordNew by rememberSaveable{ mutableStateOf(false) }
    var showPasswordOld by rememberSaveable{ mutableStateOf(false) }
    var isErrorPassNew by rememberSaveable { mutableStateOf("") }
    var isErrorPassOld by rememberSaveable { mutableStateOf("") }

    Dialog(
        onDismissRequest = {
            closeDialog()
        },
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        )
    ) {
        val scope = rememberCoroutineScope()
        val snackbarHostState = remember { SnackbarHostState() }

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
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                    ) {
                        Row(){
                            Text(
                                text = stringResource(R.string.change_password),
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
                                Icon(imageVector = Icons.Default.Close, contentDescription = stringResource(id = R.string.close))
                            }
                        }

                        Text(
                            text = stringResource(R.string.new_password),
                            color = MaterialTheme.colorScheme.onSurface,
                            style = MaterialTheme.typography.titleSmall,
                            modifier = Modifier
                                .padding(top = 40.dp)
                        )
                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = inputNewPassword,
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = MaterialTheme.colorScheme.primary,
                                unfocusedBorderColor = MaterialTheme.colorScheme.outline,
                                focusedLabelColor = MaterialTheme.colorScheme.primary,
                                unfocusedLabelColor = MaterialTheme.colorScheme.outline,
                                errorLabelColor = MaterialTheme.colorScheme.error,
                            ),
                            textStyle = TextStyle.Default.copy(
                                fontSize = 16.sp
                            ),
                            onValueChange = {inputNewPassword=it},
                            isError = isErrorPassNew != "",
                            singleLine = true,
                            trailingIcon = {
                                IconButton(
                                    onClick = { showPasswordNew = !showPasswordNew }
                                ) {
                                    Icon(
                                        painter = if (showPasswordNew) {
                                            painterResource(id = R.drawable.baseline_visibility_24)
                                        } else {
                                            painterResource(id = R.drawable.baseline_visibility_off_24)
                                        },
                                        contentDescription = stringResource(R.string.visibility_password)
                                    )
                                }
                            },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Password
                            ),
                            visualTransformation = if (showPasswordNew) VisualTransformation.None else PasswordVisualTransformation()
                        )
                        ErrorPasswordMessage(isErrorPassNew)


                        Text(
                            text = stringResource(R.string.old_password),
                            color = MaterialTheme.colorScheme.onSurface,
                            style = MaterialTheme.typography.titleSmall,
                            modifier = Modifier
                                .padding(top = 17.dp)
                        )
                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = inputOldPassword,
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = MaterialTheme.colorScheme.primary,
                                unfocusedBorderColor = MaterialTheme.colorScheme.outline,
                                focusedLabelColor = MaterialTheme.colorScheme.primary,
                                unfocusedLabelColor = MaterialTheme.colorScheme.outline,
                                errorLabelColor = MaterialTheme.colorScheme.error,
                            ),
                            textStyle = TextStyle.Default.copy(
                                fontSize = 16.sp
                            ),
                            onValueChange = {inputOldPassword=it},
                            isError = isErrorPassOld != "",
                            singleLine = true,
                            trailingIcon = {
                                IconButton(
                                    onClick = { showPasswordOld = !showPasswordOld }
                                ) {
                                    Icon(
                                        painter = if (showPasswordOld) {
                                            painterResource(id = R.drawable.baseline_visibility_24)
                                        } else {
                                            painterResource(id = R.drawable.baseline_visibility_off_24)
                                        },
                                        contentDescription = stringResource(R.string.visibility_password)
                                    )
                                }
                            },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Password
                            ),
                            visualTransformation = if (showPasswordOld) VisualTransformation.None else PasswordVisualTransformation()
                        )
                        ErrorPasswordMessage(isErrorPassOld)

                        Button(
                            onClick = {
                                isErrorPassNew = passwordValidation(inputNewPassword)
                                isErrorPassOld = passwordValidation(inputOldPassword)
                                if (inputNewPassword!="" && inputOldPassword!=""){
                                    if (inputNewPassword!=inputOldPassword){
                                        scope.launch {
                                            viewModel.editPassword(token,userId,inputOldPassword,inputNewPassword).asFlow().collect {
                                                when (it) {
                                                    is ResultState.Loading -> {
                                                        showLoading = true
                                                    }

                                                    is ResultState.Success -> {
                                                        showLoading = false
                                                        val result = snackbarHostState.showSnackbar(
                                                            message = context.getString(R.string.success_edit_password),
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
                                                            message = context.getString(R.string.failed_to_edit_your_password),
                                                            withDismissAction = false,
                                                            duration = SnackbarDuration.Short,
                                                        )
                                                    }
                                                }
                                            }
                                        }
                                    }else{
                                        closeDialog()
                                    }

                                }else{
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
                                text = "Submit",
                                color = MaterialTheme.colorScheme.onPrimary,
                                style = MaterialTheme.typography.bodyLarge.copy(
                                    fontWeight = FontWeight.Normal,
                                    fontStyle = FontStyle.Normal
                                )
                            )
                        }
                    }
                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun EditPasswordUserPreview() {
    EditPasswordUser(token = "", userId = 1, closeDialog = { })
}