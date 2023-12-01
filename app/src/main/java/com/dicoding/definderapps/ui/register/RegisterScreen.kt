package com.dicoding.definderapps.ui.register

import android.widget.Toast
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dicoding.definderapps.R
import com.dicoding.definderapps.ui.component.validation.emailValidation
import com.dicoding.definderapps.ui.component.validation.errorEmailMessage
import com.dicoding.definderapps.ui.component.validation.errorNameMessage
import com.dicoding.definderapps.ui.component.validation.errorPasswordMessage
import com.dicoding.definderapps.ui.component.validation.errorUsernameMessage
import com.dicoding.definderapps.ui.component.validation.nameValidation
import com.dicoding.definderapps.ui.component.validation.passwordValidation
import com.dicoding.definderapps.ui.component.validation.usernameValidation
import com.dicoding.definderapps.ui.theme.DefinderAppsTheme
import com.dicoding.definderapps.utils.WindowInfo
import com.dicoding.definderapps.utils.rememberWindowInfo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen() {
    val fontFamily = FontFamily(
        Font(R.font.poppins_black, FontWeight.Black),
        Font(R.font.poppins_blackitalic, FontWeight.Black),
        Font(R.font.poppins_bold, FontWeight.Bold),
        Font(R.font.poppins_bolditalic, FontWeight.Bold),
        Font(R.font.poppins_extrabold, FontWeight.ExtraBold),
        Font(R.font.poppins_extrabolditalic, FontWeight.ExtraBold),
        Font(R.font.poppins_extralight, FontWeight.ExtraLight),
        Font(R.font.poppins_extralightitalic, FontWeight.ExtraLight),
        Font(R.font.poppins_regular, FontWeight.Normal),
        Font(R.font.poppins_italic, FontWeight.Normal),
        Font(R.font.poppins_light, FontWeight.Light),
        Font(R.font.poppins_lightitalic, FontWeight.Light),
        Font(R.font.poppins_medium, FontWeight.Medium),
        Font(R.font.poppins_mediumitalic, FontWeight.Medium),
        Font(R.font.poppins_semibold, FontWeight.SemiBold),
        Font(R.font.poppins_semibolditalic, FontWeight.SemiBold),
        Font(R.font.poppins_thin, FontWeight.Thin),
        Font(R.font.poppins_thinitalic, FontWeight.Thin),
    )
    var emailState by rememberSaveable { mutableStateOf("") }
    var usernameState by rememberSaveable { mutableStateOf("") }
    var nameState by rememberSaveable { mutableStateOf("") }
    var passwordState by rememberSaveable { mutableStateOf("") }

    var isErrorEmail by rememberSaveable { mutableStateOf("") }
    var isErrorUsername by rememberSaveable { mutableStateOf("") }
    var isErrorName by rememberSaveable { mutableStateOf("") }
    var isErrorPass by rememberSaveable { mutableStateOf("") }


    val context = androidx.compose.ui.platform.LocalContext.current
    val windowInfo = rememberWindowInfo()
    var passwordVisibility by remember { mutableStateOf(false) }
    var iconVisibility = if (passwordVisibility){
        painterResource(id = R.drawable.baseline_visibility_24)
    } else {
        painterResource(id = R.drawable.baseline_visibility_off_24)
    }

    //potrait
    if (windowInfo.screenWidthInfo is WindowInfo.WindowType.Compact) {
        LazyColumn{
            item {
                Column(
                    modifier = Modifier.padding(16.dp),
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
                        fontFamily = fontFamily,
                        color = Color(0xFF000080),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle.Normal,
                    )
                    Text(
                        text = stringResource(id = R.string.register_info),
                        fontFamily = fontFamily,
                        color = Color(0xFF79747E),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal,
                        fontStyle = FontStyle.Normal,
                    )
                    Text(
                        modifier = Modifier.padding(top = 16.dp),
                        text = stringResource(id = R.string.username),
                        fontFamily = fontFamily,
                        color = Color(0xFF000080),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal,
                        fontStyle = FontStyle.Normal,
                    )
                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        value = usernameState,
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Color(0xFF000080),
                            unfocusedBorderColor = Color(0xFFCBCCCFF),
                        ),
                        onValueChange = {
                            usernameState = it
                        },
                        isError = if (isErrorUsername!="") true else false,
                        singleLine = true,
                    )

                    errorUsernameMessage(isErrorUsername)

                    Text(
                        modifier = Modifier.padding(top = 16.dp),
                        text = stringResource(id = R.string.email),
                        fontFamily = fontFamily,
                        color = Color(0xFF000080),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal,
                        fontStyle = FontStyle.Normal,
                    )
                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        value = emailState,
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Color(0xFF000080),
                            unfocusedBorderColor = Color(0xFFCBCCCFF),
                        ),
                        onValueChange = {
                            emailState = it
                        },
                        isError = if(isErrorEmail!="") true else false,
                        singleLine = true,
                    )

                    errorEmailMessage(isErrorEmail)

                    Text(
                        modifier = Modifier.padding(top = 16.dp),
                        text = stringResource(id = R.string.name),
                        fontFamily = fontFamily,
                        color = Color(0xFF000080),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal,
                        fontStyle = FontStyle.Normal,
                    )
                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        value = nameState,
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Color(0xFF000080),
                            unfocusedBorderColor = Color(0xFFCBCCCFF),
                        ),
                        onValueChange = {
                            nameState = it
                        },
                        isError = if (isErrorName!="")true else false,
                        singleLine = true,
                    )

                    errorNameMessage(isErrorName)

                    Text(
                        modifier = Modifier.padding(top = 16.dp),
                        text = stringResource(id= R.string.password),
                        fontFamily = fontFamily,
                        color = Color(0xFF000080),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal,
                        fontStyle = FontStyle.Normal,
                    )
                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        value = passwordState,
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Color(0xFF000080),
                            unfocusedBorderColor = Color(0xFFCBCCCFF),
                        ),
                        onValueChange = {
                            passwordState = it
                        },
                        isError=if (isErrorPass!="")true else false,
                        singleLine = true,
                        trailingIcon = {
                            IconButton(onClick = {
                                passwordVisibility = !passwordVisibility
                            }) {
                                Icon(
                                    painter = iconVisibility,
                                    contentDescription = "visibility password")
                            }
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password
                        ),
                        visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation()
                    )

                    errorPasswordMessage(isErrorPass)

                    Button(
                        onClick = {
                            isErrorUsername= usernameValidation(usernameState)
                            isErrorEmail = emailValidation(emailState)
                            isErrorName = nameValidation(nameState)
                            isErrorPass = passwordValidation(passwordState)
                            //Toast.makeText(context, "Registration Success", Toast.LENGTH_SHORT).show()
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp),
                        colors = ButtonDefaults.buttonColors(Color(0xFF000080)),
                        shape = RoundedCornerShape(4.dp)
                    ) {
                        Text(
                            modifier = Modifier.padding(6.dp),
                            text = stringResource(id = R.string.register),
                            fontFamily = fontFamily,
                            color = Color(0xFFE6E6F2),
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal,
                            fontStyle = FontStyle.Normal,
                        )
                    }
                    Row(
                        modifier = Modifier
                            .padding(top = 10.dp)
                            .align(Alignment.CenterHorizontally),
                        verticalAlignment =Alignment.CenterVertically,
                        horizontalArrangement =Arrangement.Center,
                    ) {
                        Text(
                            text = stringResource(id=R.string.already_account),
                            fontFamily = fontFamily,
                            color = Color(0xFF79747E),
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Normal,
                            fontStyle = FontStyle.Normal,
                        )
                        Text(
                            modifier = Modifier
                                .padding(start = 3.dp)
                                .clickable {
                                    Toast
                                        .makeText(
                                            context,
                                            "Menuju halaman login",
                                            Toast.LENGTH_SHORT
                                        )
                                        .show()
                                },
                            text = stringResource(id = R.string.login),
                            fontFamily = fontFamily,
                            color = Color(0xFF000080),
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Normal,
                            fontStyle = FontStyle.Normal,
                        )
                    }
                }
            }
        }

        //Landscape
    } else {
        Row (
            modifier = Modifier
                .padding(horizontal = 32.dp, vertical = 8.dp)
                .fillMaxSize(),
        ){
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1.5f)
                    .padding(end = 8.dp)
            ) {
                Text(
                    modifier = Modifier.padding(start=16.dp),
                    text = stringResource(id = R.string.register),
                    fontFamily = fontFamily,
                    color = Color(0xFF000080),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Normal,
                )
                Text(
                    modifier = Modifier.padding(start=16.dp),
                    text = stringResource(id = R.string.register_info),
                    fontFamily = fontFamily,
                    color = Color(0xFF79747E),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    fontStyle = FontStyle.Normal,
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
            ){
                item {
                    Column {
                        Text(
                            modifier = Modifier.padding(top = 16.dp),
                            text = stringResource(id = R.string.username),
                            fontFamily = fontFamily,
                            color = Color(0xFF000080),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal,
                            fontStyle = FontStyle.Normal,
                        )
                        OutlinedTextField(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp),
                            value = usernameState,
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = Color(0xFF000080),
                                unfocusedBorderColor = Color(0xFFCBCCCFF),
                            ),
                            onValueChange = {
                                usernameState = it
                            },
                            isError = if (isErrorUsername!="") true else false,
                            singleLine = true,
                        )

                        errorUsernameMessage(isErrorUsername)

                        Text(
                            modifier = Modifier.padding(top = 16.dp),
                            text = stringResource(id = R.string.email),
                            fontFamily = fontFamily,
                            color = Color(0xFF000080),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal,
                            fontStyle = FontStyle.Normal,
                        )
                        OutlinedTextField(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp),
                            value = emailState,
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = Color(0xFF000080),
                                unfocusedBorderColor = Color(0xFFCBCCCFF),
                            ),
                            onValueChange = {
                                emailState = it
                            },
                            isError = if(isErrorEmail!="") true else false,
                            singleLine = true,
                        )

                        errorEmailMessage(isErrorEmail)

                        Text(
                            modifier = Modifier.padding(top = 16.dp),
                            text = stringResource(id = R.string.name),
                            fontFamily = fontFamily,
                            color = Color(0xFF000080),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal,
                            fontStyle = FontStyle.Normal,
                        )
                        OutlinedTextField(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp),
                            value = nameState,
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = Color(0xFF000080),
                                unfocusedBorderColor = Color(0xFFCBCCCFF),
                            ),
                            onValueChange = {
                                nameState = it
                            },
                            isError = if (isErrorName!="")true else false,
                            singleLine = true,
                        )

                        errorNameMessage(isErrorName)

                        Text(
                            modifier = Modifier.padding(top = 16.dp),
                            text = stringResource(id= R.string.password),
                            fontFamily = fontFamily,
                            color = Color(0xFF000080),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal,
                            fontStyle = FontStyle.Normal,
                        )
                        OutlinedTextField(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp),
                            value = passwordState,
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = Color(0xFF000080),
                                unfocusedBorderColor = Color(0xFFCBCCCFF),
                            ),
                            onValueChange = {
                                passwordState = it
                            },
                            isError=if (isErrorPass!="")true else false,
                            singleLine = true,
                            trailingIcon = {
                                IconButton(onClick = {
                                    passwordVisibility = !passwordVisibility
                                }) {
                                    Icon(
                                        painter = iconVisibility,
                                        contentDescription = "visibility password")
                                }
                            },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Password
                            ),
                            visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation()
                        )

                        errorPasswordMessage(isErrorPass)

                        Button(
                            onClick = {
                                isErrorUsername= usernameValidation(usernameState)
                                isErrorEmail = emailValidation(emailState)
                                isErrorName = nameValidation(nameState)
                                isErrorPass = passwordValidation(passwordState)
                                //Toast.makeText(context, "Registration Success", Toast.LENGTH_SHORT).show()
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 10.dp),
                            colors = ButtonDefaults.buttonColors(Color(0xFF000080)),
                            shape = RoundedCornerShape(4.dp)
                        ) {
                            Text(
                                modifier = Modifier.padding(6.dp),
                                text = stringResource(id = R.string.register),
                                fontFamily = fontFamily,
                                color = Color(0xFFE6E6F2),
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Normal,
                                fontStyle = FontStyle.Normal,
                            )
                        }
                        Row(
                            modifier = Modifier
                                .padding(top = 10.dp)
                                .align(Alignment.CenterHorizontally),
                            verticalAlignment =Alignment.CenterVertically,
                            horizontalArrangement =Arrangement.Center,
                        ) {
                            Text(
                                text = stringResource(id=R.string.already_account),
                                fontFamily = fontFamily,
                                color = Color(0xFF79747E),
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Normal,
                                fontStyle = FontStyle.Normal,
                            )
                            Text(
                                modifier = Modifier
                                    .padding(start = 3.dp)
                                    .clickable {
                                        Toast
                                            .makeText(
                                                context,
                                                "Menuju halaman login",
                                                Toast.LENGTH_SHORT
                                            )
                                            .show()
                                    },
                                text = stringResource(id = R.string.login),
                                fontFamily = fontFamily,
                                color = Color(0xFF000080),
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Normal,
                                fontStyle = FontStyle.Normal,
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
        RegisterScreen()
    }
}