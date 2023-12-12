package com.dicoding.definderapps.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
fun LoginPotrait(
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
) {
    Column(
        modifier = modifier.padding(16.dp),
    ) {
        Image(
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth()
                .height(201.dp),
            painter = painterResource(id = R.drawable.definder_login_page),
            contentDescription = stringResource(R.string.login_image)
        )
        Text(
            text = stringResource(id = R.string.login),
            color = Color(0xFF000080),
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Normal
            )
        )
        Text(
            text = stringResource(id = R.string.login_info),
            color = Color(0xFF79747E),
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.Normal,
                fontStyle = FontStyle.Normal
            )
        )

        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
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
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            onClick = btnSignIn,
            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
            shape = RoundedCornerShape(4.dp)
        ) {
            Text(
                modifier = Modifier.padding(vertical = 4.dp),
                text = stringResource(id = R.string.login),
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
                text = stringResource(id = R.string.not_yet_account),
                color = Color(0xFF79747E),
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Normal,
                    fontStyle = FontStyle.Normal
                )
            )
            Text(
                modifier = Modifier
                    .padding(start = 3.dp)
                    .clickable {navigateToRegister()},
                text = stringResource(id = R.string.register),
                color = Color(0xFF000080),
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Normal,
                    fontStyle = FontStyle.Normal
                )
            )
        }
    }
}