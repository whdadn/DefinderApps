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
import com.dicoding.definderapps.ui.component.validation.ErrorEmailMessage
import com.dicoding.definderapps.ui.component.validation.ErrorNameMessage
import com.dicoding.definderapps.ui.component.validation.emailValidation
import com.dicoding.definderapps.ui.component.validation.nameValidation
import com.dicoding.definderapps.ui.profile.ProfileViewModel
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun EditAccountUser(
    token:String,
    userId:Int,
    name:String,
    email:String,
    closeDialog:()->Unit,
    context:Context = LocalContext.current,
    viewModel:ProfileViewModel = viewModel(factory=ViewModelFactory.getInstance(context))
){
    var inputEditName by rememberSaveable { mutableStateOf("") }
    var inputEditEmail by rememberSaveable{ mutableStateOf("") }
    inputEditName = name
    inputEditEmail = email
    var showLoading by rememberSaveable{ mutableStateOf(false) }
    var isErrorEmail by rememberSaveable { mutableStateOf("") }
    var isErrorName by rememberSaveable { mutableStateOf("") }

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
                                text = stringResource(R.string.edit_account),
                                color = Color(0xFF000080),
                                style = MaterialTheme.typography.headlineMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    fontStyle = FontStyle.Normal
                                ),
                                modifier = Modifier
                                    .weight(2f)
                                    .padding(top = 40.dp)
                            )
                            IconButton(onClick = closeDialog) {
                                Icon(imageVector = Icons.Default.Close, contentDescription = null)
                            }
                        }

                        Text(
                            text = stringResource(id = R.string.name),
                            color = Color(0xFF00002D),
                            style = MaterialTheme.typography.titleSmall,
                            modifier = Modifier
                                .padding(top = 40.dp)
                        )
                        OutlinedTextField(
                            modifier = Modifier
                                .fillMaxWidth(),
                            value = inputEditName,
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Color(0xFF000080),
                                unfocusedBorderColor = Color(0xFFBCCCFF),
                            ),
                            textStyle = TextStyle.Default.copy(
                                fontSize = 16.sp
                            ),
                            onValueChange = { inputEditName = it },
                            singleLine = true,
                            maxLines = 1
                        )
                        ErrorNameMessage(isErrorName)

                        Text(
                            text = stringResource(id = R.string.email),
                            color = Color(0xFF00002D),
                            style = MaterialTheme.typography.titleSmall,
                            modifier = Modifier
                                .padding(top = 20.dp)
                        )
                        OutlinedTextField(
                            modifier = Modifier
                                .fillMaxWidth(),
                            value = inputEditEmail,
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Color(0xFF000080),
                                unfocusedBorderColor = Color(0xFFBCCCFF),
                            ),
                            textStyle = TextStyle.Default.copy(
                                fontSize = 16.sp
                            ),
                            onValueChange = { inputEditEmail = it },
                            singleLine = true,
                            maxLines = 1
                        )

                        ErrorEmailMessage(isErrorEmail)

                        Button(
                            onClick = {
                                isErrorName = nameValidation(inputEditName)
                                isErrorEmail = emailValidation(inputEditEmail)
                                if (inputEditName!="" && inputEditEmail!=""){
                                    if (inputEditName!=name || inputEditEmail!=email){
                                        scope.launch {
                                            viewModel.editAccount(token,userId,name, email.lowercase()).asFlow().collect {
                                                when (it) {
                                                    is ResultState.Loading -> {
                                                        showLoading = true
                                                    }

                                                    is ResultState.Success -> {
                                                        showLoading = false
                                                        val result = snackbarHostState.showSnackbar(
                                                            message = context.getString(R.string.success_edit_account),
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
                                                            message = context.getString(R.string.failed_to_edit_your_account),
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
                            colors = ButtonDefaults.buttonColors(Color(0xFF000080)),
                            shape = RoundedCornerShape(4.dp)
                        ) {
                            Text(
                                modifier = Modifier.padding(vertical = 4.dp),
                                text = "Submit",
                                color = Color(0xFFE6E6F2),
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
fun EditNameUserPreview()
{
    EditAccountUser(closeDialog = {}, token = "", userId = 1, name = "Umar", email = "umar@me.co")
}