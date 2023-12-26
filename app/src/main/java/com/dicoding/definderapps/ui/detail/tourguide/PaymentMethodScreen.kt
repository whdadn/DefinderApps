package com.dicoding.definderapps.ui.detail.tourguide

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.dicoding.definderapps.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentMethodScreen() {
    Box(Modifier.fillMaxSize()) {
        Column(Modifier.align(Alignment.Center)) {
            Text(
                text = stringResource(R.string.coming_soon),
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Normal,
                    fontStyle = FontStyle.Italic
                )
            )
        }
    }

//    var expanded by remember { mutableStateOf(false) }
//    var option by remember { mutableStateOf("") }
//    var cardName by rememberSaveable { mutableStateOf("") }
//    var cardNumber by rememberSaveable { mutableStateOf("") }
//
//    Column(
//        modifier = Modifier
//            .padding(16.dp),
//    ) {
//        Text(
//            text = "Payment Method",
//            color = MaterialTheme.colorScheme.onSurface,
//            style = MaterialTheme.typography.headlineMedium.copy(
//                fontWeight = FontWeight.Bold,
//                fontStyle = FontStyle.Normal
//            ),
//            modifier = Modifier.padding(vertical = 10.dp),
//        )
//        Text(
//            text = "Choose Card",
//            color = MaterialTheme.colorScheme.onSurface,
//            style = MaterialTheme.typography.titleSmall.copy(
//                fontWeight = FontWeight.Normal,
//                fontStyle = FontStyle.Normal
//            ),
//            modifier = Modifier.padding(vertical = 8.dp)
//        )
//        ExposedDropdownMenuBox(
//            expanded = expanded,
//            onExpandedChange = { expanded = it },
//        ) {
//            OutlinedTextField(
//                value = option,
//                onValueChange = {},
//                readOnly = true,
//                trailingIcon = {
//                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
//                },
//                modifier = Modifier.menuAnchor(),
//                colors = OutlinedTextFieldDefaults.colors(
//                    focusedBorderColor = MaterialTheme.colorScheme.primary,
//                    unfocusedBorderColor = MaterialTheme.colorScheme.outline,
//                ),
//                placeholder = {
//                    Text(
//                        text = "Choose Card",
//                        color = MaterialTheme.colorScheme.onSurface,
//                        style = MaterialTheme.typography.bodyMedium
//                    )
//                }
//            )
//            ExposedDropdownMenu(
//                expanded = expanded,
//                onDismissRequest = { expanded = false },
//            ) {
//                DropdownMenuItem(
//                    text = { Text(text = "Master Card") },
//                    onClick = {
//                        option = "Master Card"
//                        expanded = false
//                    },
//                )
//                DropdownMenuItem(
//                    text = { Text(text = "Cimb Niaga") },
//                    onClick = {
//                        option = "Cimb Niaga"
//                        expanded = false
//                    },
//                )
//                DropdownMenuItem(
//                    text = { Text(text = "BCA") },
//                    onClick = {
//                        option = "BCA"
//                        expanded = false
//                    },
//                )
//                DropdownMenuItem(
//                    text = { Text(text = "BNI") },
//                    onClick = {
//                        option = "BNI"
//                        expanded = false
//                    },
//                )
//            }
//        }
//        Text(
//            text = "Card Name",
//            color = MaterialTheme.colorScheme.onSurface,
//            style = MaterialTheme.typography.titleSmall.copy(
//                fontWeight = FontWeight.Normal,
//                fontStyle = FontStyle.Normal
//            ),
//            modifier = Modifier.padding(vertical = 8.dp)
//        )
//        OutlinedTextField(
//            modifier = Modifier
//                .fillMaxWidth(),
//            value = cardName,
//            colors = OutlinedTextFieldDefaults.colors(
//                focusedBorderColor = MaterialTheme.colorScheme.primary,
//                unfocusedBorderColor = MaterialTheme.colorScheme.outline,
//            ),
//            textStyle = TextStyle.Default.copy(
//                fontSize = 16.sp
//            ),
//            onValueChange = { cardName = it },
//        )
//        Text(
//            text = "Card Number",
//            color = MaterialTheme.colorScheme.onSurface,
//            style = MaterialTheme.typography.titleSmall.copy(
//                fontWeight = FontWeight.Normal,
//                fontStyle = FontStyle.Normal
//            ),
//            modifier = Modifier.padding(vertical = 8.dp)
//        )
//        OutlinedTextField(
//            modifier = Modifier
//                .fillMaxWidth(),
//            value = cardNumber,
//            colors = OutlinedTextFieldDefaults.colors(
//                focusedBorderColor = MaterialTheme.colorScheme.primary,
//                unfocusedBorderColor = MaterialTheme.colorScheme.outline,
//            ),
//            textStyle = TextStyle.Default.copy(
//                fontSize = 16.sp
//            ),
//            onValueChange = { cardNumber = it },
//        )
//
//        Button(
//            onClick = { },
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(top = 17.dp),
//            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
//            shape = RoundedCornerShape(4.dp)
//        ) {
//            Text(
//                modifier = Modifier.padding(vertical = 4.dp),
//                text = "Purchase",
//                color = MaterialTheme.colorScheme.onPrimary,
//                style = MaterialTheme.typography.bodyLarge.copy(
//                    fontWeight = FontWeight.Normal,
//                    fontStyle = FontStyle.Normal
//                )
//            )
//        }
//    }
}

@Preview(
    showBackground = true
)
@Composable
fun PaymentMethodScreenPreview() {
    PaymentMethodScreen()
}