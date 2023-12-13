package com.dicoding.definderapps.ui.mbti

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
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

@Composable
fun MbtiScreen()
{
    var inputMbti by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .padding(16.dp)
    ) {
        Text(
            text = "Let’s check your \npersonality",
            color = Color(0xFF000080),
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Normal
            )
        )
        Text(
            text = "Tell us about your self",
            color = Color(0xFF00002D),
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier
                .padding(top = 17.dp)
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = inputMbti,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF000080),
                unfocusedBorderColor = Color(0xFFBCCCFF),
            ),
            textStyle = TextStyle.Default.copy(
                fontSize = 16.sp
            ),
            onValueChange = { inputMbti = it },
        )

        Button(
            onClick = {  },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 17.dp),
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
    showBackground = true,
    showSystemUi = true
)
@Composable
fun MbtiScreenPreview()
{
    MbtiScreen()
}