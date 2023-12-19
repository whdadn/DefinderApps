package com.dicoding.definderapps.ui.component.detail.tourguide

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dicoding.definderapps.R

@Composable
fun TourGuideItem(
    imageUrl: Int,
    name: String,
    rating: String,
    favorite: Boolean,
    favoriteChange: (Boolean) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(vertical = 10.dp)
    ) {
        Card(
            modifier = Modifier
                .wrapContentSize()
                .border(2.dp, Color(0xFF000080) ,shape = RoundedCornerShape(20.dp))
                .padding(vertical = 7.dp),
            colors = CardDefaults.cardColors(Color.Transparent),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    painter = painterResource(id = imageUrl),
                    contentDescription = "profile",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .padding(start = 10.dp, end = 10.dp)
                        .size(45.dp)
                        .clip(CircleShape),
                )
                Column(
                    modifier = Modifier
                        .weight(1f)
                ) {
                    Text(
                        text = name,
                        color = Color(0xFF000080),
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            fontStyle = FontStyle.Normal
                        ),
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_star_24),
                            contentDescription = "rating",
                            tint = Color.Yellow,
                            modifier = Modifier
                                .size(17.dp)
                        )
                        Text(
                            text = rating,
                            color = Color(0xFF79747E),
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontWeight = FontWeight.Normal,
                                fontStyle = FontStyle.Normal
                            )
                        )
                        IconToggleButton(
                            checked = favorite,
                            onCheckedChange = favoriteChange,
                            modifier = Modifier
                                .size(25.dp)
                                .padding(start = 10.dp)
                        ){
                            Icon(
                                painter = if (favorite){
                                    painterResource(id = R.drawable.baseline_favorite_24)
                                } else {
                                    painterResource(id = R.drawable.baseline_favorite_border_24)
                                },
                                contentDescription = stringResource(R.string.favorite),
                                tint = Color(0xFF000066)
                            )
                        }
                    }
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
                            text = "Message",
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
fun TourGuideItemPreview() {
    TourGuideItem(
        imageUrl = R.drawable.borobudur2,
        name = "Sukuna",
        rating = "4 (120)",
        favorite = false,
        favoriteChange = {}
    )
}