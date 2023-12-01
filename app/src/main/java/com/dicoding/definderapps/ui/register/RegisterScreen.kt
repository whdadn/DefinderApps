package com.dicoding.definderapps.ui.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dicoding.definderapps.R
import com.dicoding.definderapps.ui.component.form.PassTextField
import com.dicoding.definderapps.ui.component.form.TextField
import com.dicoding.definderapps.ui.component.validation.emailValidation
import com.dicoding.definderapps.ui.component.validation.ErrorEmailMessage
import com.dicoding.definderapps.ui.component.validation.ErrorNameMessage
import com.dicoding.definderapps.ui.component.validation.ErrorPasswordMessage
import com.dicoding.definderapps.ui.component.validation.ErrorUsernameMessage
import com.dicoding.definderapps.ui.component.validation.nameValidation
import com.dicoding.definderapps.ui.component.validation.passwordValidation
import com.dicoding.definderapps.ui.component.validation.usernameValidation
import com.dicoding.definderapps.ui.theme.DefinderAppsTheme
import com.dicoding.definderapps.utils.WindowInfo
import com.dicoding.definderapps.utils.rememberWindowInfo

@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,
    navigateToLogin: () -> Unit
) {
    var emailState by rememberSaveable { mutableStateOf("") }
    var usernameState by rememberSaveable { mutableStateOf("") }
    var nameState by rememberSaveable { mutableStateOf("") }
    var passwordState by rememberSaveable { mutableStateOf("") }

    var isErrorEmail by rememberSaveable { mutableStateOf("") }
    var isErrorUsername by rememberSaveable { mutableStateOf("") }
    var isErrorName by rememberSaveable { mutableStateOf("") }
    var isErrorPass by rememberSaveable { mutableStateOf("") }

    val windowInfo = rememberWindowInfo()
    var passwordVisibility by remember { mutableStateOf(false) }


    //potrait
    if (windowInfo.screenWidthInfo is WindowInfo.WindowType.Compact) {
        LazyColumn {
            item {
                Column(
                    modifier = modifier.padding(16.dp),
                ) {
                    Image(
                        modifier = Modifier
                            .padding(top = 16.dp)
                            .fillMaxWidth()
                            .height(201.dp),
                        painter = painterResource(id = R.drawable.definder_register_page),
                        contentDescription = stringResource(id = R.string.register_image)
                    )
                    Text(
                        text = stringResource(id = R.string.register),
                        color = Color(0xFF000080),
                        style = MaterialTheme.typography.headlineMedium.copy(
                            fontWeight = FontWeight.Bold,
                            fontStyle = FontStyle.Normal
                        )
                    )
                    Text(
                        text = stringResource(id = R.string.register_info),
                        color = Color(0xFF79747E),
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.Normal,
                            fontStyle = FontStyle.Normal
                        )
                    )

                    TextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top=8.dp),
                        value = usernameState,
                        onValueChange = { usernameState = it },
                        label = stringResource(id = R.string.username),
                        isError = isErrorUsername
                    )
                    ErrorUsernameMessage(isErrorUsername)

                    TextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top=4.dp),
                        value = emailState,
                        onValueChange = { emailState = it },
                        label = stringResource(id = R.string.email),
                        isError = isErrorEmail
                    )
                    ErrorEmailMessage(isErrorEmail)

                    TextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top=4.dp),
                        value = nameState,
                        onValueChange = { nameState = it },
                        label = stringResource(id = R.string.name),
                        isError = isErrorName
                    )
                    ErrorNameMessage(isErrorName)

                    PassTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top=4.dp),
                        value = passwordState,
                        onValueChange = { passwordState = it },
                        label = stringResource(id = R.string.password),
                        isError = isErrorPass,
                        passwordVisibility = { passwordVisibility = !passwordVisibility },
                        showPassword = passwordVisibility
                    )
                    ErrorPasswordMessage(isErrorPass)

                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp),
                        onClick = {
                            isErrorUsername = usernameValidation(usernameState)
                            isErrorEmail = emailValidation(emailState)
                            isErrorName = nameValidation(nameState)
                            isErrorPass = passwordValidation(passwordState)
                            //Toast.makeText(context, "Registration Success", Toast.LENGTH_SHORT).show()
                        },
                        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
                        shape = RoundedCornerShape(4.dp)
                    ) {
                        Text(
                            modifier = Modifier.padding(vertical = 4.dp),
                            text = stringResource(id = R.string.register),
                            color = Color(0xFFE6E6F2),
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontWeight = FontWeight.Normal,
                                fontStyle = FontStyle.Normal
                            )
                        )
                    }
                    Row(
                        modifier = Modifier
                            .padding(top = 10.dp)
                            .align(Alignment.CenterHorizontally),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        Text(
                            text = stringResource(id = R.string.already_account),
                            color = Color(0xFF79747E),
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.Normal,
                                fontStyle = FontStyle.Normal
                            )
                        )
                        Text(
                            modifier = Modifier
                                .padding(start = 3.dp)
                                .clickable {navigateToLogin() },
                            text = stringResource(id = R.string.login),
                            color = Color(0xFF000080),
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.Normal,
                                fontStyle = FontStyle.Normal
                            )
                        )
                    }
                }
            }
        }

        //Landscape
    } else {
        Row(
            modifier = modifier
                .padding(horizontal = 32.dp, vertical = 8.dp)
                .fillMaxSize(),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1.5f)
                    .padding(end = 8.dp)
            ) {
                Text(
                    modifier = Modifier.padding(start = 16.dp),
                    text = stringResource(id = R.string.register),
                    color = Color(0xFF000080),
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle.Normal
                    )
                )
                Text(
                    modifier = Modifier.padding(start = 16.dp),
                    text = stringResource(id = R.string.register_info),
                    color = Color(0xFF79747E),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Normal,
                        fontStyle = FontStyle.Normal
                    )

                )
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp),
                    painter = painterResource(id = R.drawable.definder_register_page),
                    contentDescription = stringResource(id = R.string.register_image)
                )
            }
            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {
                item {
                    Column {
                        TextField(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top=8.dp),
                            value = usernameState,
                            onValueChange = { usernameState = it },
                            label = stringResource(id = R.string.username),
                            isError = isErrorUsername
                        )
                        ErrorUsernameMessage(isErrorUsername)

                        TextField(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top=4.dp),
                            value = emailState,
                            onValueChange = { emailState = it },
                            label = stringResource(id = R.string.email),
                            isError = isErrorEmail
                        )
                        ErrorEmailMessage(isErrorEmail)

                        TextField(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top=4.dp),
                            value = nameState,
                            onValueChange = { nameState = it },
                            label = stringResource(id = R.string.name),
                            isError = isErrorName
                        )
                        ErrorNameMessage(isErrorName)

                        PassTextField(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top=4.dp),
                            value = passwordState,
                            onValueChange = { passwordState = it },
                            label = stringResource(id = R.string.password),
                            isError = isErrorPass,
                            passwordVisibility = { passwordVisibility = !passwordVisibility },
                            showPassword = passwordVisibility
                        )
                        ErrorPasswordMessage(isErrorPass)

                        Button(
                            onClick = {
                                isErrorUsername = usernameValidation(usernameState)
                                isErrorEmail = emailValidation(emailState)
                                isErrorName = nameValidation(nameState)
                                isErrorPass = passwordValidation(passwordState)
                                //Toast.makeText(context, "Registration Success", Toast.LENGTH_SHORT).show()
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 16.dp),
                            colors = ButtonDefaults.buttonColors(Color(0xFF000080)),
                            shape = RoundedCornerShape(4.dp)
                        ) {
                            Text(
                                modifier = Modifier.padding(vertical = 4.dp),
                                text = stringResource(id = R.string.register),
                                color = Color(0xFFE6E6F2),
                                style = MaterialTheme.typography.bodyLarge.copy(
                                    fontWeight = FontWeight.Normal,
                                    fontStyle = FontStyle.Normal
                                )
                            )
                        }
                        Row(
                            modifier = Modifier
                                .padding(top = 10.dp)
                                .align(Alignment.CenterHorizontally),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center,
                        ) {
                            Text(
                                text = stringResource(id = R.string.already_account),
                                color = Color(0xFF79747E),
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    fontWeight = FontWeight.Normal,
                                    fontStyle = FontStyle.Normal
                                )
                            )
                            Text(
                                modifier = Modifier
                                    .padding(start = 3.dp)
                                    .clickable {navigateToLogin()},
                                text = stringResource(id = R.string.login),
                                color = Color(0xFF000080),
                                style = MaterialTheme.typography.bodyMedium.copy(
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

@Preview(
    showBackground = true,
    device = Devices.PIXEL_4_XL,
    showSystemUi = true,
)
@Composable
fun GreetingPreview() {
    DefinderAppsTheme {
        RegisterScreen(navigateToLogin ={})
    }
}