package com.dicoding.definderapps.ui.detail.amusementrides

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.dicoding.definderapps.R
import com.dicoding.definderapps.ui.component.detail.amusementrides.AmusementRideItem

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
fun AmusementRidesScreen()
{
    LazyColumn{
        items(data){ list ->
            AmusementRideItem(image = list.image, title = list.title, price = list.price)
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
fun AmusementRidesScreenPreview()
{
    AmusementRidesScreen()
}