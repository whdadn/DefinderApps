package com.dicoding.definderapps.ui.component.detail.tourguide

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TourGuidePurchaseItem(
    duration: String,
    price: Double
) {
    Column(
        modifier = Modifier
            .padding(vertical = 10.dp)
    ) {
        Card(
            modifier = Modifier
                .wrapContentSize()
                .border(2.dp, Color(0xFF000080), shape = RoundedCornerShape(10.dp))
                .padding(vertical = 7.dp),
            colors = CardDefaults.cardColors(Color.Transparent),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 10.dp)
                ) {
                    Text(
                        text = duration,
                        color = Color(0xFF000080),
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold,
                            fontStyle = FontStyle.Normal
                        ),
                    )
                    Text(
                        text = "$$price",
                        color = Color(0xFF000080),
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.Normal,
                            fontStyle = FontStyle.Normal
                        ),
                    )
                }

                Column {
                    Button(
                        onClick = { /*TODO*/ },
                        modifier = Modifier
                            .padding(end = 10.dp),
                        colors = ButtonDefaults.buttonColors(Color(0xFF000080)),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text(
                            text = "Purchase",
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontWeight = FontWeight.Normal,
                                fontStyle = FontStyle.Normal
                            )
                        )
                    }
                }
            }
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
fun TourGuidePurchaseItemPreview()
{
    TourGuidePurchaseItem(duration = "1 Montly", price = 10.0)
}