package com.dicoding.definderapps.ui.component.detail.tourguide

import androidx.compose.foundation.Image
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
                .padding(vertical = 7.dp),
            colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primaryContainer),
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
                        color = MaterialTheme.colorScheme.onTertiaryContainer,
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
                            tint = MaterialTheme.colorScheme.onTertiaryContainer,
                            modifier = Modifier
                                .size(17.dp)
                                .padding(bottom = 3.dp)
                        )
                        Text(
                            text = rating,
                            color = MaterialTheme.colorScheme.onTertiaryContainer,
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
                                tint = MaterialTheme.colorScheme.onTertiaryContainer
                            )
                        }
                    }
                }

                Column {
                    Button(
                        onClick = { /*TODO*/ },
                        modifier = Modifier
                            .padding(end = 10.dp),
                        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text(
                            text = "Message",
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontWeight = FontWeight.Normal,
                                fontStyle = FontStyle.Normal
                            ),
                            color = MaterialTheme.colorScheme.onPrimary
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
}