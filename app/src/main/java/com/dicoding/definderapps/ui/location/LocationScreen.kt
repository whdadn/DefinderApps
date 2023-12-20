package com.dicoding.definderapps.ui.location

import android.annotation.SuppressLint
import android.content.Context
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
import com.dicoding.definderapps.R
import com.dicoding.definderapps.data.local.pref.HomeLocModel
import com.dicoding.definderapps.ui.welcome.WelcomeViewModel
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LocationScreen(
    closeDialog:()->Unit,
    context:Context = LocalContext.current,
    viewModel:WelcomeViewModel,
    navigateToHome:()->Unit
) {
    var inputTourismName by rememberSaveable { mutableStateOf("") }
    var inputProvince by rememberSaveable { mutableStateOf("") }

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
                        .padding(16.dp)
                ) {
                        Row(){
                            Text(
                                text = stringResource(R.string.location_info),
                                color = Color(0xFF000080),
                                style = MaterialTheme.typography.headlineMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    fontStyle = FontStyle.Normal
                                ),
                                modifier = Modifier.weight(2f).padding(top=40.dp)
                            )
                            IconButton(onClick = closeDialog) {
                                Icon(imageVector = Icons.Default.Close, contentDescription = null)
                            }
                        }

                    Text(
                        text = stringResource(R.string.tourism_name),
                        color = Color(0xFF00002D),
                        style = MaterialTheme.typography.titleSmall,
                        modifier = Modifier
                            .padding(top = 17.dp)
                    )
                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth(),
                        value = inputTourismName,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color(0xFF000080),
                            unfocusedBorderColor = Color(0xFFBCCCFF),
                        ),
                        textStyle = TextStyle.Default.copy(
                            fontSize = 16.sp
                        ),
                        onValueChange = { inputTourismName = it },
                    )

                    Text(
                        text = stringResource(R.string.province_name),
                        color = Color(0xFF00002D),
                        style = MaterialTheme.typography.titleSmall,
                        modifier = Modifier
                            .padding(top = 17.dp)
                    )
                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth(),
                        value = inputProvince,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color(0xFF000080),
                            unfocusedBorderColor = Color(0xFFBCCCFF),
                        ),
                        textStyle = TextStyle.Default.copy(
                            fontSize = 16.sp
                        ),
                        onValueChange = { inputProvince = it },
                    )

                    Button(
                        onClick = {
                                  if (inputTourismName!="" && inputProvince!=""){
                                      viewModel.saveHomeContent("location")
                                      val loc = HomeLocModel(name = inputTourismName, province = inputProvince)
                                      viewModel.saveHomeLoc(loc)
                                      navigateToHome()

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
                            text = stringResource(R.string.submit),
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

@Preview(
    showBackground = true
)
@Composable
fun LocationScreenPreview()
{}