package com.dicoding.definderapps.ui.detail.transportation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AddTransportation() {
    val option = listOf(
        "Land Transportation",
        "Air Transportation",
        "Sea Transportation"
    )
    var selectedOption by remember { mutableStateOf("") }
    var nameTransportation by rememberSaveable { mutableStateOf("") }
    var explainFlow by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .padding(16.dp),
    ) {
        Card(
            modifier = Modifier
                .wrapContentSize()
                .fillMaxWidth()
                .padding(bottom = 20.dp),
            colors = CardDefaults.cardColors(Color.Transparent),
            border = BorderStroke(2.dp, Color(0xFF000080))
        ) {
            Text(
                text = "Select the type transportation type",
                color = Color(0xFF00002D),
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Normal,
                    fontStyle = FontStyle.Normal
                ),
                modifier = Modifier
                    .padding(7.dp)
            )
            option.forEach { options ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .selectable(
                            selected = selectedOption == options,
                            onClick = {selectedOption = options}
                        ),
                ) {
                    RadioButton(
                        selected = selectedOption == options,
                        onClick = { selectedOption = options },
                        colors = RadioButtonDefaults.colors(Color(0xFF000080))
                    )
                    Text(
                        text = options,
                        color = Color(0xFF00002D),
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Normal,
                            fontStyle = FontStyle.Normal
                        )
                    )
                }
            }
        }

        Card(
            modifier = Modifier
                .wrapContentSize()
                .fillMaxWidth()
                .padding(bottom = 20.dp),
            colors = CardDefaults.cardColors(Color.Transparent),
            border = BorderStroke(2.dp, Color(0xFF000080)),
        ) {
            Text(
                text = "Name Transportation",
                color = Color(0xFF00002D),
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Normal,
                    fontStyle = FontStyle.Normal
                ),
                modifier = Modifier
                    .padding(7.dp)
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 15.dp, end = 15.dp, top = 28.dp, bottom = 15.dp),
                value = nameTransportation,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF000080),
                    unfocusedBorderColor = Color(0xFFBCCCFF),
                ),
                textStyle = TextStyle.Default.copy(
                    fontSize = 16.sp
                ),
                onValueChange = { nameTransportation = it },
                maxLines = 1,
                shape = RoundedCornerShape(7.dp)
            )
        }
        Card(
            modifier = Modifier
                .wrapContentSize()
                .fillMaxWidth()
                .padding(bottom = 20.dp),
            colors = CardDefaults.cardColors(Color.Transparent),
            border = BorderStroke(2.dp, Color(0xFF000080)),
        ) {
            Text(
                text = "Explain the flow of travel to \ntourist attractions",
                color = Color(0xFF00002D),
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Normal,
                    fontStyle = FontStyle.Normal
                ),
                modifier = Modifier
                    .padding(7.dp)
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 15.dp, end = 15.dp, top = 28.dp, bottom = 15.dp),
                value = explainFlow,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF000080),
                    unfocusedBorderColor = Color(0xFFBCCCFF),
                ),
                textStyle = TextStyle.Default.copy(
                    fontSize = 16.sp
                ),
                onValueChange = {explainFlow = it },
                maxLines = 1,
                shape = RoundedCornerShape(7.dp)
            )
        }
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            onClick = {  },
            colors = ButtonDefaults.buttonColors(Color(0xFF000080)),
            shape = RoundedCornerShape(4.dp)
        ) {
            Text(
                modifier = Modifier.padding(vertical = 4.dp),
                text = "Submit",
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
fun AddTransportationPreview() {
    AddTransportation()
}