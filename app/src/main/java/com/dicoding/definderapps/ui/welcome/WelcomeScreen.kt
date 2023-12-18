package com.dicoding.definderapps.ui.welcome

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dicoding.definderapps.R

@Composable
fun WelcomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Search for tourists \nbased on your wishes",
            color = Color(0xFF000080),
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Normal
            ),
            modifier = Modifier
                .padding(bottom = 34.dp),
        )

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround,
        ) {
            Card(
                shape = RoundedCornerShape(10.dp),
                colors = CardDefaults.cardColors(Color(0xFF000080)),
                modifier = Modifier
                    .width(135.dp)
                    .height(104.dp)
                    .clickable {  }
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.location_circle),
                        contentDescription = "icon_location",
                        tint = Color(0xFF00F0FF),
                        modifier = Modifier
                            .size(50.dp)
                    )
                    Text(
                        text = "Location",
                        color = Color.White,
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Normal,
                            fontStyle = FontStyle.Normal
                        )
                    )
                }
            }

            Card(
                shape = RoundedCornerShape(10.dp),
                colors = CardDefaults.cardColors(Color(0xFF000080)),
                modifier = Modifier
                    .width(135.dp)
                    .height(104.dp)
                    .clickable {  }
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.mbti),
                        contentDescription = "icon_location",
                        tint = Color(0xFF00F0FF),
                        modifier = Modifier
                            .size(50.dp)
                    )
                    Text(
                        text = "MBTI",
                        color = Color.White,
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Normal,
                            fontStyle = FontStyle.Normal
                        )
                    )
                }
            }
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
fun WelcomeScreenPreview() {
    WelcomeScreen()
}