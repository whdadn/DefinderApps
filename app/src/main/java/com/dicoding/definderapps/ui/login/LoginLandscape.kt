package com.dicoding.definderapps.ui.login

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.dicoding.definderapps.R
import com.dicoding.definderapps.ui.component.form.PassTextField
import com.dicoding.definderapps.ui.component.form.TextField
import com.dicoding.definderapps.ui.component.validation.ErrorEmailMessage
import com.dicoding.definderapps.ui.component.validation.ErrorPasswordMessage

@Composable
fun LoginLandscape(
    modifier:Modifier = Modifier,
    email:String,
    emailChange: (String)->Unit,
    pass:String,
    passChange:(String)->Unit,
    passVisibility:Boolean,
    passVisibilityClick:()->Unit,
    isErrorEmail:String,
    isErrorPass:String,
    btnSignIn:()->Unit,
    navigateToRegister:()->Unit,
){
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
                text = stringResource(id = R.string.login),
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Normal
                )
            )
            Text(
                modifier = Modifier.padding(start = 16.dp),
                text = stringResource(id = R.string.login_info),
                color = MaterialTheme.colorScheme.outline,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Normal,
                    fontStyle = FontStyle.Normal
                )

            )
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                painter = painterResource(id = R.drawable.definder_login_page),
                contentDescription = stringResource(id = R.string.login_image)
            )
        }
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(top = 64.dp),
        ) {
            TextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = email,
                onValueChange = emailChange,
                label = stringResource(id = R.string.email),
                isError = isErrorEmail
            )
            ErrorEmailMessage(isErrorEmail)

            PassTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp),
                value = pass,
                onValueChange = passChange,
                label = stringResource(id = R.string.password),
                isError = isErrorPass,
                passwordVisibility = passVisibilityClick,
                showPassword = passVisibility
            )
            ErrorPasswordMessage(isErrorPass)

            Button(
                onClick = btnSignIn,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
                shape = RoundedCornerShape(4.dp)
            ) {
                Text(
                    modifier = Modifier.padding(vertical = 4.dp),
                    text = stringResource(id = R.string.login),
                    color = MaterialTheme.colorScheme.onPrimary,
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
                    text = stringResource(id = R.string.not_yet_account),
                    color = MaterialTheme.colorScheme.outline,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Normal,
                        fontStyle = FontStyle.Normal
                    )
                )
                Text(
                    modifier = Modifier
                        .padding(start = 3.dp)
                        .clickable { navigateToRegister() },
                    text = stringResource(id = R.string.register),
                    color = MaterialTheme.colorScheme.secondary,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Normal,
                        fontStyle = FontStyle.Normal
                    )
                )
            }
        }

    }
}