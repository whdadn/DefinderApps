package com.dicoding.definderapps.ui.component.detail.reviews

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.dicoding.definderapps.R

@Composable
fun ReviewsItem(
    image: Int,
    name: String,
    review: String
)
{
    var expanded by remember { mutableStateOf(false) }
    var measuredHeight by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .padding(top = 16.dp)
    ) {
        Card(
            modifier = Modifier
                .wrapContentSize(),
            colors = CardDefaults.cardColors(Color.Transparent),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = image),
                    contentDescription = "profile",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .padding(6.dp)
                        .size(35.dp)
                        .clip(CircleShape),
                )
                Text(
                    text = name,
                    color = Color(0xFF000080),
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle.Normal
                    )
                )
            }
            Text(
                text = review,
                color = Color(0xFF00002D),
                modifier = Modifier
                    .padding(6.dp),
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Normal,
                    fontStyle = FontStyle.Normal
                ),
                textAlign = TextAlign.Left,
                maxLines = if (expanded) Int.MAX_VALUE else 5,
                overflow = TextOverflow.Ellipsis,
                onTextLayout = {textLayoutResult ->
                    if (textLayoutResult.didOverflowHeight)
                    {
                        measuredHeight = textLayoutResult.size.height
                    }
                }
            )

           if (measuredHeight > 0 && measuredHeight > 5 * MaterialTheme.typography.bodyLarge.fontSize.value)
           {
               if (!expanded)
               {
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
               }
               else
               {
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
               LazyRow(
                   contentPadding = PaddingValues(horizontal = 5.dp)
               ){
                   items(5)
                   {
                       Image(
                           painter = painterResource(id = R.drawable.candi_dieng),
                           contentDescription = null,
                           modifier = Modifier
                               .padding(end = 5.dp)
                       )
                   }
               }
           }
        }
    }
}