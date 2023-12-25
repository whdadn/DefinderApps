package com.dicoding.definderapps.ui.detail.tourguide

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dicoding.definderapps.R
import com.dicoding.definderapps.ui.component.detail.tourguide.TourGuidePurchaseItem

data class Purchase(
    val duration: String,
    val price: Double
)

val dataPurchase = listOf(
    Purchase(
        "Montly",
        10.0
    ),
    Purchase(
        "Half a year",
        10.5
    ),
    Purchase(
        "Yearly",
        11.0
    ),
)
@Composable
fun TourGuidePurchaseScreen() {
    LazyColumn{
        item {
            Column(
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                Text(
                    text = "To be a tour guide",
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle.Normal
                    ),
                    modifier = Modifier
                        .padding(top = 16.dp),
                )
            }
        }

        items(dataPurchase){list ->
            Column(
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                TourGuidePurchaseItem(
                    duration = list.duration,
                    price = list.price
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TourGuidePurchaseScreenPreview()
{
    TourGuidePurchaseScreen()
}