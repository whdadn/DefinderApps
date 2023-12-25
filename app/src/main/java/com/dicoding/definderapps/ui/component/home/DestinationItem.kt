package com.dicoding.definderapps.ui.component.home

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.dicoding.definderapps.R


@SuppressLint("DiscouragedApi")
@Composable
fun DestinationItem(
    name:String,
    imageUrl:String,
    location:String,
    rating:String,
    review:Int,
    favorite:Boolean,
    favoriteChange:(Boolean)->Unit,
    modifier:Modifier = Modifier
){
    Card(
        shape = RoundedCornerShape(15.dp),
        colors= CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier.wrapContentHeight()

    ) {
        AsyncImage(
            model = imageUrl,
            contentDescription = stringResource(R.string.image)+name,
            modifier = Modifier
                .fillMaxWidth()
                .height(121.dp),
            contentScale = ContentScale.FillWidth
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Column(
                modifier= Modifier.weight(1f)
            ) {
                Text(
                    text = name,
                    color = MaterialTheme.colorScheme.onTertiaryContainer,
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle.Normal
                    ),
                    modifier = Modifier
                        .padding(start = 10.dp, top = 3.dp)
                )
                Row(modifier = Modifier
                    .padding(start = 6.dp, top = 2.dp )){
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_location_on_24),
                        contentDescription = stringResource(R.string.icon_location),
                        modifier = Modifier
                            .size(20.dp),
                        tint = MaterialTheme.colorScheme.onTertiaryContainer
                    )
                    Text(
                        text = location,
                        color = MaterialTheme.colorScheme.onTertiaryContainer,
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.Normal,
                            fontStyle = FontStyle.Normal
                        ),
                        modifier = Modifier
                    )
                }
                Row(
                    modifier=Modifier
                        .padding(start=7.dp, top = 6.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_star_24),
                        contentDescription = stringResource(R.string.icon_rating),
                        modifier = Modifier
                            .size(20.dp),
                        tint = MaterialTheme.colorScheme.onTertiaryContainer
                    )
                    Text(
                        text = LocalContext.current.getString(R.string.rating_destination, rating.toFloat()) + "("+review+")",
                        color = MaterialTheme.colorScheme.onTertiaryContainer,
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.Normal,
                            fontStyle = FontStyle.Normal
                        ),
                        modifier = Modifier.padding(vertical = 2.dp)
                    )
                }

            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                IconToggleButton(
                    checked = favorite,
                    onCheckedChange = favoriteChange,
                    modifier = Modifier
                        .size(40.dp)
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
    }
}

@Preview(
    showBackground = true
)
@Composable
fun DestinationItemPreview(){
    DestinationItem(name= "Candi Borobudur", imageUrl = "candi_borobudur", location = "Magelang", rating = "4 ",review=1000, favorite = false, favoriteChange = {} )
}