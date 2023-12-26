package com.dicoding.definderapps.ui.detail.tourguide

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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

//data class Purchase(
//    val duration: String,
//    val price: Double
//)
//
//val dataPurchase = listOf(
//    Purchase(
//        "Montly",
//        10.0
//    ),
//    Purchase(
//        "Half a year",
//        10.5
//    ),
//    Purchase(
//        "Yearly",
//        11.0
//    ),
//)
@Composable
fun TourGuidePurchaseScreen() {
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
//    LazyColumn{
//        item {
//            Column(
//                modifier = Modifier.padding(horizontal = 16.dp)
//            ) {
//                Text(
//                    text = "To be a tour guide",
//                    color = MaterialTheme.colorScheme.onSurface,
//                    style = MaterialTheme.typography.headlineMedium.copy(
//                        fontWeight = FontWeight.Bold,
//                        fontStyle = FontStyle.Normal
//                    ),
//                    modifier = Modifier
//                        .padding(top = 16.dp),
//                )
//            }
//        }
//
//        items(dataPurchase){list ->
//            Column(
//                modifier = Modifier.padding(horizontal = 16.dp)
//            ) {
//                TourGuidePurchaseItem(
//                    duration = list.duration,
//                    price = list.price
//                )
//            }
//        }
//    }
}

@Preview(showBackground = true)
@Composable
fun TourGuidePurchaseScreenPreview()
{
    TourGuidePurchaseScreen()
}