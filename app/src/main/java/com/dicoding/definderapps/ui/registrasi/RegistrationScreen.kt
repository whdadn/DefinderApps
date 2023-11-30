package com.dicoding.definderapps.ui.registrasi

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import com.dicoding.definderapps.ui.welcome.theme.DefinderAppsTheme
import com.dicoding.definderapps.utils.WindowInfo
import com.dicoding.definderapps.utils.rememberWindowInfo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterPage() {
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
    var emailState by rememberSaveable {
        mutableStateOf("")
    }
    var nameState by rememberSaveable {
        mutableStateOf("")
    }
    var passwordState by remember {
        mutableStateOf("")
    }
    val context = androidx.compose.ui.platform.LocalContext.current
    val windowInfo = rememberWindowInfo()
    var passwordVisibility by remember { mutableStateOf(false) }
    var iconVisibility = if (passwordVisibility){
        painterResource(id = R.drawable.baseline_visibility_24)
    }
    else
    {
        painterResource(id = R.drawable.baseline_visibility_off_24)
    }

    if (windowInfo.screenWidthInfo is WindowInfo.WindowType.Compact) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                modifier = Modifier
                    .padding(25.dp)
                    .width(344.dp)
                    .height(201.dp),
                painter = painterResource(id = R.drawable.definder_register_page),
                contentDescription = "Register Image"
            )
            Text(
                modifier = Modifier
                    .padding(horizontal = 34.dp)
                    .align(Alignment.Start),
                text = "Register",
                fontFamily = fontFamily,
                color = Color(0xFF000080),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Normal,
            )
            Text(
                modifier = Modifier
                    .padding(horizontal = 34.dp)
                    .align(Alignment.Start),
                text = "Before exploring tourist destinations, \nlet's create an account first",
                fontFamily = fontFamily,
                color = Color(0xFF79747E),
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                fontStyle = FontStyle.Normal,
            )
            Text(
                modifier = Modifier
                    .padding(start = 34.dp, top = 16.dp)
                    .align(Alignment.Start),
                text = "Email",
                fontFamily = fontFamily,
                color = Color(0xFF000080),
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                fontStyle = FontStyle.Normal,
            )
            OutlinedTextField(
                modifier = Modifier
                    .padding(horizontal = 34.dp)
                    .align(Alignment.Start)
                    .fillMaxWidth(),
                value = emailState,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0xFF000080),
                    unfocusedBorderColor = Color(0xFFCBCCCFF),
                ),
                onValueChange = {
                    emailState = it
                },
                singleLine = true,
            )

            Text(
                modifier = Modifier
                    .padding(start = 34.dp, top = 16.dp)
                    .align(Alignment.Start),
                text = "Name",
                fontFamily = fontFamily,
                color = Color(0xFF000080),
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                fontStyle = FontStyle.Normal,
            )
            OutlinedTextField(
                modifier = Modifier
                    .padding(horizontal = 34.dp)
                    .align(Alignment.Start)
                    .fillMaxWidth(),
                value = nameState,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0xFF000080),
                    unfocusedBorderColor = Color(0xFFCBCCCFF),
                ),
                onValueChange = {
                    nameState = it
                },
                singleLine = true,
            )

            Text(
                modifier = Modifier
                    .padding(start = 34.dp, top = 16.dp)
                    .align(Alignment.Start),
                text = "Password",
                fontFamily = fontFamily,
                color = Color(0xFF000080),
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                fontStyle = FontStyle.Normal,
            )
            OutlinedTextField(
                modifier = Modifier
                    .padding(horizontal = 34.dp)
                    .align(Alignment.Start)
                    .fillMaxWidth(),
                value = passwordState,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0xFF000080),
                    unfocusedBorderColor = Color(0xFFCBCCCFF),
                ),
                onValueChange = {
                    passwordState = it
                },
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
                visualTransformation = if (passwordVisibility) VisualTransformation.None
                else PasswordVisualTransformation()
            )
            Button(
                onClick = {
                    Toast.makeText(context, "Registration Success", Toast.LENGTH_SHORT).show()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 34.dp, vertical = 10.dp),
                colors = ButtonDefaults.buttonColors(Color(0xFF000080)),
                shape = RoundedCornerShape(4.dp)
            ) {
                Text(
                    modifier = Modifier.align(Alignment.CenterVertically),
                    text = "Register",
                    fontFamily = fontFamily,
                    color = Color(0xFFE6E6F2),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    fontStyle = FontStyle.Normal,
                )
            }

            Row {
                Text(
                    modifier = Modifier
                        .padding(top = 7.dp)
                        .align(Alignment.CenterVertically),
                    text = "Already have an account?",
                    fontFamily = fontFamily,
                    color = Color(0xFF79747E),
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Normal,
                    fontStyle = FontStyle.Normal,
                )
                Text(
                    modifier = Modifier
                        .padding(start = 3.dp, top = 7.dp)
                        .align(Alignment.CenterVertically)
                        .clickable {
                            Toast
                                .makeText(context, "Menuju halaman login", Toast.LENGTH_SHORT)
                                .show()
                        },
                    text = "Login",
                    fontFamily = fontFamily,
                    color = Color(0xFF000080),
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Normal,
                    fontStyle = FontStyle.Normal,
                )
            }
        }
    } else {
        Column {
            Text(
                modifier = Modifier.padding(horizontal = 34.dp),
                text = "Register",
                fontFamily = fontFamily,
                color = Color(0xFF000080),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Normal,
            )
            Text(
                modifier = Modifier.padding(horizontal = 34.dp),
                text = "Before exploring tourist destinations, \nlet's create an account first",
                fontFamily = fontFamily,
                color = Color(0xFF79747E),
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                fontStyle = FontStyle.Normal,
            )
        }
        Row(
            modifier = Modifier
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                modifier = Modifier
                    .padding(25.dp)
                    .width(344.dp)
                    .height(201.dp),
                painter = painterResource(id = R.drawable.definder_register_page),
                contentDescription = "Register Image"
            )
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier
                        .padding(start = 34.dp, top = 28.dp)
                        .align(Alignment.Start),
                    text = "Email",
                    fontFamily = fontFamily,
                    color = Color(0xFF000080),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    fontStyle = FontStyle.Normal,
                )
                OutlinedTextField(
                    modifier = Modifier
                        .padding(horizontal = 34.dp)
                        .align(Alignment.Start)
                        .height(50.dp)
                        .fillMaxWidth(),
                    value = emailState,
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color(0xFF000080),
                        unfocusedBorderColor = Color(0xFFCBCCCFF),
                    ),
                    onValueChange = {
                        emailState = it
                    },
                    singleLine = true,
                )

                Text(
                    modifier = Modifier
                        .padding(start = 34.dp, top = 16.dp)
                        .align(Alignment.Start),
                    text = "Name",
                    fontFamily = fontFamily,
                    color = Color(0xFF000080),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    fontStyle = FontStyle.Normal,
                )
                OutlinedTextField(
                    modifier = Modifier
                        .padding(horizontal = 34.dp)
                        .align(Alignment.Start)
                        .height(50.dp)
                        .fillMaxWidth(),
                    value = nameState,
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color(0xFF000080),
                        unfocusedBorderColor = Color(0xFFCBCCCFF),
                    ),
                    onValueChange = {
                        nameState = it
                    },
                    singleLine = true,
                )

                Text(
                    modifier = Modifier
                        .padding(start = 34.dp, top = 16.dp)
                        .align(Alignment.Start),
                    text = "Password",
                    fontFamily = fontFamily,
                    color = Color(0xFF000080),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    fontStyle = FontStyle.Normal,
                )
                OutlinedTextField(
                    modifier = Modifier
                        .padding(horizontal = 34.dp)
                        .align(Alignment.Start)
                        .height(50.dp)
                        .fillMaxWidth(),
                    value = passwordState,
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color(0xFF000080),
                        unfocusedBorderColor = Color(0xFFCBCCCFF),
                    ),
                    onValueChange = {
                        passwordState = it
                    },
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
                    visualTransformation = if (passwordVisibility) VisualTransformation.None
                    else PasswordVisualTransformation()
                )
                Button(
                    onClick = {
                        Toast.makeText(context, "Registration Success", Toast.LENGTH_SHORT).show()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 34.dp, vertical = 10.dp),
                    colors = ButtonDefaults.buttonColors(Color(0xFF000080)),
                    shape = RoundedCornerShape(4.dp)
                ) {
                    Text(
                        modifier = Modifier.align(Alignment.CenterVertically),
                        text = "Register",
                        fontFamily = fontFamily,
                        color = Color(0xFFE6E6F2),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal,
                        fontStyle = FontStyle.Normal,
                    )
                }

                Spacer(modifier = Modifier.height(2.dp))
                Row {
                    Text(
                        modifier = Modifier
                            .align(Alignment.CenterVertically),
                        text = "Already have an account?",
                        fontFamily = fontFamily,
                        color = Color(0xFF79747E),
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Normal,
                        fontStyle = FontStyle.Normal,
                    )
                    Text(
                        modifier = Modifier
                            .padding(start = 3.dp, top = 1.dp, bottom = 2.dp)
                            .clickable {
                                Toast
                                    .makeText(context, "Menuju halaman login", Toast.LENGTH_SHORT)
                                    .show()
                            },
                        text = "Login",
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

@Preview(
    showBackground = true,
    device = Devices.PIXEL_4_XL,
    showSystemUi = true,
)
@Composable
fun GreetingPreview() {
    DefinderAppsTheme {
        RegisterPage()
    }
}