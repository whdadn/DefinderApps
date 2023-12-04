package com.dicoding.definderapps.ui.component.detail.amusementrides

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dicoding.definderapps.R

data class AmusementRides(
    val id: Int, 
    val image: Int,
    val title: String,
    val price: Double
)

val data = listOf(
    AmusementRides(
        1,
        R.drawable.butterfly_photobooth,
        "Butterfly Photobooth",
        5.00
    ),
    AmusementRides(
        2,
        R.drawable.bioskop_4d,
        "Bioskop 4 Dimensi",
        1.92
    ),
    AmusementRides(
        3,
        R.drawable.terapi_ikan,
        "Terapi Ikan",
        0.32
    )
)
@Composable
fun AmusementRideItem(
    amusementRides: AmusementRides
)
{
    Column(
        modifier = Modifier
            .padding(16.dp)
    ) {
        Card(
            modifier = Modifier
                .wrapContentSize(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(Color.Transparent),
            border = BorderStroke(2.dp, Color(0xFF000080))
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = amusementRides.image),
                    contentDescription = "image",
                    modifier = Modifier
                        .size(100.dp)
                        .clip(RoundedCornerShape(16.dp)),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(8.dp))

                Column {
                    Text(
                        text = amusementRides.title,
                        color = Color(0xFF000080),
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            fontStyle = FontStyle.Normal
                        )
                    )
                    Row {
                        Icon(
                            painter = painterResource(id = R.drawable.ticket_fill),
                            contentDescription = "ticket",
                            modifier = Modifier
                                .size(22.dp),
                            tint = Color(0xFF000080)
                        )
                        Text(
                            text = "$" + amusementRides.price.toString(),
                            color = Color(0xFF000080),
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontWeight = FontWeight.Bold,
                                fontStyle = FontStyle.Normal
                            ),
                            modifier = Modifier
                                .padding(start = 5.dp)
                        )
                    }
                }

            }
        }
    }
}

@Composable
fun AmusementRidesListItems()
{
    LazyColumn{
        items(data){ list ->
            AmusementRideItem(amusementRides = list)
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    device = Devices.PIXEL_4_XL
)
@Composable
fun AmusementRideItemsPreview()
{
    AmusementRidesListItems()
}