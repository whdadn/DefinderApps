package com.dicoding.definderapps.ui.mbti

import android.annotation.SuppressLint
import android.content.Context
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
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
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
import com.dicoding.definderapps.R
import com.dicoding.definderapps.ui.welcome.WelcomeViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MbtiScreen(
    closeDialog: () -> Unit,
    context: Context = LocalContext.current,
    navigateToHome: () -> Unit,
    viewModel: WelcomeViewModel
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

        Scaffold(
            snackbarHost = {
                SnackbarHost(
                    hostState = snackbarHostState,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
            }
        ) {
            Surface(modifier = Modifier.fillMaxSize()) {
                Column(
                    modifier = Modifier
                        .padding(16.dp),
                ) {
                    Row() {
                        Text(
                            text = stringResource(R.string.info_mbti),
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
                        text = stringResource(R.string.mbti_form),
                        color = Color(0xFF00002D),
                        style = MaterialTheme.typography.titleSmall,
                        modifier = Modifier
                            .padding(top = 17.dp)
                    )
                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth(),
                        value = inputMbti,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color(0xFF000080),
                            unfocusedBorderColor = Color(0xFFBCCCFF),
                        ),
                        textStyle = TextStyle.Default.copy(
                            fontSize = 16.sp
                        ),
                        onValueChange = { inputMbti = it },
                    )

                    Button(
                        onClick = {
                            if (inputMbti.isNotBlank()) {
//                                      viewModel.saveHomeContent("mbti")
//                                      val mbti = HomeMbtiModel(personality= inputMbti)
//                                      viewModel.saveHomeMbti(mbti)
//                                      navigateToHome()

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
                    Divider(
                        thickness = 2.dp,
                        modifier = Modifier
                            .padding(top = 15.dp)
                    )
                    Text(
                        text = "Your personality type is:",
                        modifier = Modifier
                            .padding(top = 10.dp)
                            .align(Alignment.CenterHorizontally),
                        style = MaterialTheme.typography.titleSmall.copy(
                            fontWeight = FontWeight.Normal,
                            fontStyle = FontStyle.Normal
                        ),
                        color = Color(0xFF00002D),
                    )
                    Text(
                        text = "ISTJ",
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(top = 10.dp),
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold,
                            fontStyle = FontStyle.Normal
                        ),
                        color = Color(0xFF00002D),
                    )
                    LazyColumn {
                        item {
                            Text(
                                text = "Logisticians are practical and fact-minded individuals, whose reliability cannot be doubted.",
                                textAlign = TextAlign.Justify,
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    fontWeight = FontWeight.Normal,
                                    fontStyle = FontStyle.Normal
                                ),
                                color = Color(0xFF00002D),
                                modifier = Modifier
                                    .padding(top = 10.dp),
                            )
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
}