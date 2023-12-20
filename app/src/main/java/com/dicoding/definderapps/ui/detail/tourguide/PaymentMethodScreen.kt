package com.dicoding.definderapps.ui.detail.tourguide

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentMethodScreen() {
    var expanded by remember { mutableStateOf(false) }
    var option by remember { mutableStateOf("") }
    var cardName by rememberSaveable { mutableStateOf("") }
    var cardNumber by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .padding(16.dp),
    ) {
        Text(
            text = "Payment Method",
            color = Color(0xFF000080),
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Normal
            ),
            modifier = Modifier.padding(vertical = 10.dp),
        )
        Text(
            text = "Choose Card",
            color = Color(0xFF000080),
            style = MaterialTheme.typography.titleSmall.copy(
                fontWeight = FontWeight.Normal,
                fontStyle = FontStyle.Normal
            ),
            modifier = Modifier.padding(vertical = 8.dp)
        )
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = it },
        ) {
            OutlinedTextField(
                value = option,
                onValueChange = {},
                readOnly = true,
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                },
                modifier = Modifier.menuAnchor(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF000080),
                    unfocusedBorderColor = Color(0xFFBCCCFF),
                ),
                placeholder = {
                    Text(
                        text = "Choose Card",
                        color = Color(0xFF000080),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
            ) {
                DropdownMenuItem(
                    text = { Text(text = "Master Card") },
                    onClick = {
                        option = "Master Card"
                        expanded = false
                    },
                )
                DropdownMenuItem(
                    text = { Text(text = "Cimb Niaga") },
                    onClick = {
                        option = "Cimb Niaga"
                        expanded = false
                    },
                )
                DropdownMenuItem(
                    text = { Text(text = "BCA") },
                    onClick = {
                        option = "BCA"
                        expanded = false
                    },
                )
                DropdownMenuItem(
                    text = { Text(text = "BNI") },
                    onClick = {
                        option = "BNI"
                        expanded = false
                    },
                )
            }
        }
        Text(
            text = "Card Name",
            color = Color(0xFF000080),
            style = MaterialTheme.typography.titleSmall.copy(
                fontWeight = FontWeight.Normal,
                fontStyle = FontStyle.Normal
            ),
            modifier = Modifier.padding(vertical = 8.dp)
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = cardName,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF000080),
                unfocusedBorderColor = Color(0xFFBCCCFF),
            ),
            textStyle = TextStyle.Default.copy(
                fontSize = 16.sp
            ),
            onValueChange = { cardName = it },
        )
        Text(
            text = "Card Number",
            color = Color(0xFF000080),
            style = MaterialTheme.typography.titleSmall.copy(
                fontWeight = FontWeight.Normal,
                fontStyle = FontStyle.Normal
            ),
            modifier = Modifier.padding(vertical = 8.dp)
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = cardNumber,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF000080),
                unfocusedBorderColor = Color(0xFFBCCCFF),
            ),
            textStyle = TextStyle.Default.copy(
                fontSize = 16.sp
            ),
            onValueChange = { cardNumber = it },
        )

        Button(
            onClick = { },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 17.dp),
            colors = ButtonDefaults.buttonColors(Color(0xFF000080)),
            shape = RoundedCornerShape(4.dp)
        ) {
            Text(
                modifier = Modifier.padding(vertical = 4.dp),
                text = "Purchase",
                color = Color(0xFFE6E6F2),
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Normal,
                    fontStyle = FontStyle.Normal
                )
            )
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
fun PaymentMethodScreenPreview() {
    PaymentMethodScreen()
}