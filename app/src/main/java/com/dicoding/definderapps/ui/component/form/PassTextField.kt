package com.dicoding.definderapps.ui.component.form

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.sp
import com.dicoding.definderapps.R

@Composable
fun PassTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    isError: String,
    passwordVisibility: () -> Unit,
    showPassword: Boolean
) {
    OutlinedTextField(
        modifier = modifier,
        value = value,
        label = {
            Text(
                text = label,
                style = MaterialTheme.typography.labelLarge.copy(
                    fontWeight = FontWeight.Normal,
                    fontStyle = FontStyle.Normal
                )

            )
        },
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
        onValueChange = onValueChange,
        isError = if (isError != "") true else false,
        singleLine = true,
        trailingIcon = {
            IconButton(
                onClick = passwordVisibility
            ) {
                Icon(
                    painter = if (showPassword) {
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
        visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation()
    )
}