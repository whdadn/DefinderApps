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
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
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

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LocationScreen(
    mbti:String,
    closeDialog:()->Unit,
    context:Context = LocalContext.current,
    viewModel:WelcomeViewModel,
    navigateToHome:()->Unit
) {
    var inputTourismName by rememberSaveable { mutableStateOf("") }
    var inputDistrict by rememberSaveable { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    var optionMbti by remember { mutableStateOf(mbti) }

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
                                Icon(imageVector = Icons.Default.Close, contentDescription = null)
                            }
                        }

                    Text(
                        text = stringResource(R.string.tourism_name),
                        color = MaterialTheme.colorScheme.onSurface,
                        style = MaterialTheme.typography.titleSmall,
                        modifier = Modifier
                            .padding(top = 17.dp)
                    )
                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth(),
                        value = inputTourismName,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = MaterialTheme.colorScheme.primary,
                            unfocusedBorderColor = MaterialTheme.colorScheme.outline,
                        ),
                        textStyle = TextStyle.Default.copy(
                            fontSize = 16.sp
                        ),
                        onValueChange = { inputTourismName = it },
                        singleLine = true,
                        maxLines = 1,
                    )

                    Text(
                        text = stringResource(R.string.district_name),
                        color = MaterialTheme.colorScheme.onSurface,
                        style = MaterialTheme.typography.titleSmall,
                        modifier = Modifier
                            .padding(top = 17.dp)
                    )
                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth(),
                        value = inputDistrict,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = MaterialTheme.colorScheme.primary,
                            unfocusedBorderColor = MaterialTheme.colorScheme.outline,
                        ),
                        textStyle = TextStyle.Default.copy(
                            fontSize = 16.sp
                        ),
                        onValueChange = { inputDistrict = it },
                        singleLine = true,
                        maxLines = 1
                    )
                    Text(
                        text = "Choose Personality",
                        color = MaterialTheme.colorScheme.onSurface,
                        style = MaterialTheme.typography.titleSmall.copy(
                            fontWeight = FontWeight.Normal,
                            fontStyle = FontStyle.Normal
                        ),
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                    ExposedDropdownMenuBox(
                        expanded = expanded,
                        onExpandedChange = { expanded = !expanded },
                    ) {
                        OutlinedTextField(
                            value = optionMbti,
                            onValueChange = {},
                            readOnly = true,
                            trailingIcon = {
                                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                            },
                            modifier = Modifier.menuAnchor(),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = MaterialTheme.colorScheme.primary,
                                unfocusedBorderColor = MaterialTheme.colorScheme.outline,
                            ),
                            placeholder = {
                                Text(
                                    text = "Choose Personality",
                                    color = MaterialTheme.colorScheme.onSurface,
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }
                        )
                        ExposedDropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false },
                        ) {
                            DropdownMenuItem(
                                text = { Text(
                                    text = "ESTJ",
                                    color = MaterialTheme.colorScheme.onSurface
                                ) },
                                onClick = {
                                    optionMbti = "ESTJ"
                                    expanded = false
                                },
                            )
                            DropdownMenuItem(
                                text = { Text(
                                    text = "ESTP",
                                    color = MaterialTheme.colorScheme.onSurface
                                ) },
                                onClick = {
                                    optionMbti = "ESTP"
                                    expanded = false
                                },
                            )
                            DropdownMenuItem(
                                text = { Text(
                                    text = "ESFP",
                                    color = MaterialTheme.colorScheme.onSurface
                                ) },
                                onClick = {
                                    optionMbti = "ESFP"
                                    expanded = false
                                },
                            )
                            DropdownMenuItem(
                                text = { Text(
                                    text = "ESFJ",
                                    color = MaterialTheme.colorScheme.onSurface
                                ) },
                                onClick = {
                                    optionMbti = "ESFJ"
                                    expanded = false
                                },
                            )
                            DropdownMenuItem(
                                text = { Text(
                                    text = "ISTJ",
                                    color = MaterialTheme.colorScheme.onSurface
                                ) },
                                onClick = {
                                    optionMbti = "ISTJ"
                                    expanded = false
                                },
                            )
                            DropdownMenuItem(
                                text = { Text(
                                    text = "ISTP",
                                    color = MaterialTheme.colorScheme.onSurface
                                ) },
                                onClick = {
                                    optionMbti = "ISTP"
                                    expanded = false
                                },
                            )
                            DropdownMenuItem(
                                text = { Text(
                                    text = "ISFP",
                                    color = MaterialTheme.colorScheme.onSurface
                                ) },
                                onClick = {
                                    optionMbti = "ISFP"
                                    expanded = false
                                },
                            )
                            DropdownMenuItem(
                                text = { Text(
                                    text = "ISFJ",
                                    color = MaterialTheme.colorScheme.onSurface
                                ) },
                                onClick = {
                                    optionMbti = "ISFJ"
                                    expanded = false
                                },
                            )
                            DropdownMenuItem(
                                text = { Text(
                                    text = "INTJ",
                                    color = MaterialTheme.colorScheme.onSurface
                                ) },
                                onClick = {
                                    optionMbti = "INTJ"
                                    expanded = false
                                },
                            )
                            DropdownMenuItem(
                                text = { Text(
                                    text = "INTP",
                                    color = MaterialTheme.colorScheme.onSurface
                                ) },
                                onClick = {
                                    optionMbti = "INTP"
                                    expanded = false
                                },
                            )
                            DropdownMenuItem(
                                text = { Text(
                                    text = "INFP",
                                    color = MaterialTheme.colorScheme.onSurface
                                ) },
                                onClick = {
                                    optionMbti = "INFP"
                                    expanded = false
                                },
                            )
                            DropdownMenuItem(
                                text = { Text(
                                    text = "INFJ",
                                    color = MaterialTheme.colorScheme.onSurface
                                ) },
                                onClick = {
                                    optionMbti = "INFJ"
                                    expanded = false
                                },
                            )
                            DropdownMenuItem(
                                text = { Text(
                                    text = "ENTJ",
                                    color = MaterialTheme.colorScheme.onSurface
                                ) },
                                onClick = {
                                    optionMbti = "ENTJ"
                                    expanded = false
                                },
                            )
                            DropdownMenuItem(
                                text = { Text(
                                    text = "ENTP",
                                    color = MaterialTheme.colorScheme.onSurface
                                ) },
                                onClick = {
                                    optionMbti = "ENTP"
                                    expanded = false
                                },
                            )
                            DropdownMenuItem(
                                text = { Text(
                                    text = "ENFP",
                                    color = MaterialTheme.colorScheme.onSurface
                                ) },
                                onClick = {
                                    optionMbti = "ENFP"
                                    expanded = false
                                },
                            )
                            DropdownMenuItem(
                                text = { Text(
                                    text = "ENFJ",
                                    color = MaterialTheme.colorScheme.onSurface
                                ) },
                                onClick = {
                                    optionMbti = "ENFJ"
                                    expanded = false
                                },
                            )
                        }
                    }

                    Button(
                        onClick = {
                                  if (inputTourismName!="" && inputDistrict!="" && optionMbti!=""){
                                      val loc = HomeLocModel(name = inputTourismName.trim(), district = inputDistrict.trim(), mbti=optionMbti)
                                      viewModel.saveHomeLoc(loc)
                                      viewModel.saveMbti(optionMbti)
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
                        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
                        shape = RoundedCornerShape(4.dp)
                    ) {
                        Text(
                            modifier = Modifier.padding(vertical = 4.dp),
                            text = stringResource(R.string.submit),
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

@Preview(
    showBackground = true
)
@Composable
fun LocationScreenPreview()
{}