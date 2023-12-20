package com.dicoding.definderapps.ui.component.detail.transportation

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@SuppressLint("DiscouragedApi")
@Composable
fun DetailTransportationItem(
    name: String,
    image: String,
    transportationName: String,
    transportationDesc: String,
    modifier: Modifier = Modifier
) {

    var expanded by remember { mutableStateOf(false) }
    var measuredHeight by remember { mutableStateOf(0) }

    Card(
        modifier = modifier
            .wrapContentSize()
            .padding(horizontal = 8.dp, vertical = 4.dp)
        ,
        colors = CardDefaults.cardColors(Color.Transparent),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val imageResourceId = LocalContext.current.resources.getIdentifier(
                image,
                "drawable",
                LocalContext.current.packageName
            )
            Image(
                painter = painterResource(imageResourceId),
                contentDescription = "profile",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(6.dp)
                    .size(30.dp)
                    .clip(CircleShape),
            )
            Text(
                text = name,
                color = Color(0xFF000080),
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Normal
                )
            )
        }

        Column(modifier = Modifier.padding(horizontal = 6.dp)) {
            Text(
                text = transportationName,
                color = Color(0xFF000080),
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Normal
                ),
            )

            Text(
                text = "\"$transportationDesc\".",
                color = Color(0xFF00002D),
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Normal,
                    fontStyle = FontStyle.Normal,
                    textAlign = TextAlign.Justify
                ),
                maxLines = if (expanded) Int.MAX_VALUE else 5,
                overflow = TextOverflow.Ellipsis,
                onTextLayout = { textLayoutResult ->
                    if (textLayoutResult.didOverflowHeight) {
                        measuredHeight = textLayoutResult.size.height
                    }
                }
            )

            if (measuredHeight > 0 && measuredHeight > 5 * MaterialTheme.typography.bodyLarge.fontSize.value) {
                if (!expanded) {
                    Text(
                        text = "Show more",
                        color = Color(0xFFb0b0d8),
                        modifier = Modifier
                            .padding(6.dp)
                            .clickable { expanded = true },
                        style = MaterialTheme.typography.labelMedium.copy(
                            fontWeight = FontWeight.Normal,
                            fontStyle = FontStyle.Normal
                        )
                    )
                } else {
                    Text(
                        text = "Show less",
                        color = Color(0xFFb0b0d8),
                        modifier = Modifier
                            .padding(6.dp)
                            .clickable { expanded = false },
                        style = MaterialTheme.typography.labelMedium.copy(
                            fontWeight = FontWeight.Normal,
                            fontStyle = FontStyle.Normal
                        )
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DetailTransportItemPreview() {
    DetailTransportationItem(
        name = "Alex Bijer",
        image = "img_profile_default",
        transportationName = "Buroq",
        transportationDesc = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus ac bibendum nibh. Donec vel ante in tellus lacinia euismod at sit amet nulla. Curabitur sed tincidunt dolor. Fusce egestas est sed dolor faucibus, eget semper mi tempus. Sed nibh ex, ultricies eget sollicitudin vitae, pretium id metus. Interdum et malesuada fames ac ante ipsum primis in faucibus. In lacinia venenatis elit, pulvinar condimentum tortor cursus ut. Pellentesque feugiat metus cursus augue elementum vestibulum. Etiam sit amet erat orci. Curabitur fermentum magna sit amet volutpat blandit. In mattis, nisl in consectetur ullamcorper, magna erat semper nisi, aliquam commodo lectus lectus in purus."
    )
}