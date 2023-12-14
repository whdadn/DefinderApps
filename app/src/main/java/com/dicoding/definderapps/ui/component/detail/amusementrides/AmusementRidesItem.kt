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
import androidx.compose.ui.unit.dp
import com.dicoding.definderapps.R


@Composable
fun AmusementRideItem(
   image: Int,
   title: String,
   price: Double
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
                    painter = painterResource(id = image),
                    contentDescription = "image",
                    modifier = Modifier
                        .size(100.dp)
                        .clip(RoundedCornerShape(16.dp)),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(8.dp))

                Column {
                    Text(
                        text = title,
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
                            text = "$$price",
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