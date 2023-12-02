package com.dicoding.definderapps.ui.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dicoding.definderapps.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage()
{
    val checkedState = remember { mutableStateOf(false) }
    var searchState by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .padding(16.dp)
    ) {
       LazyColumn(){
            item {
                TextField(
                    value = searchState,
                    onValueChange = {searchState = it},
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .clip(RoundedCornerShape(20.dp)),
                    placeholder = { Text(
                        text = "Search",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Normal,
                            fontStyle = FontStyle.Normal
                        ),
                        color = Color.Gray,
                        modifier = Modifier
                            .padding(start = 1.dp)
                            .fillMaxSize())
                    },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_search_24),
                            contentDescription = "search",
                            modifier = Modifier
                                .offset(x = 5.dp)
                                .size(25.dp),
                            tint = Color(0xFF79747E)
                        )
                    },
                    textStyle = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Normal,
                        fontStyle = FontStyle.Normal
                    ),
                    maxLines = 1
                )

                Text(
                    text = "Let's explore the \ntourism",
                    color = Color(0xFF000080),
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle.Normal
                    ),
                    modifier = Modifier
                        .padding(top = 20.dp),
                )
            }

           items(10)
           {
               Card(
                   modifier = Modifier
                       .padding(top = 6.dp)
                       .fillMaxWidth()
                       .align(Alignment.CenterHorizontally)
                       .clip(RoundedCornerShape(15.dp))
                       .height(208.dp),
                   border = BorderStroke(2.dp, Color(0xFF000080)),
                   colors = CardDefaults.cardColors(Color.White)
               ) {
                   Image(
                       painter = painterResource(id = R.drawable.borobudur),
                       contentDescription = "Borobudur Image",
                       modifier = Modifier
                           .clip(RoundedCornerShape(15.dp))
                           .fillMaxWidth()
                           .height(121.dp),
                       contentScale = ContentScale.FillWidth
                   )
                   Row(
                       modifier = Modifier
                           .fillMaxWidth(),
                   ) {
                       Text(
                           text = "Candi Borobudur",
                           color = Color(0xFF000080),
                           style = MaterialTheme.typography.titleLarge.copy(
                               fontWeight = FontWeight.Bold,
                               fontStyle = FontStyle.Normal
                           ),
                           modifier = Modifier
                               .padding(start = 10.dp, top = 3.dp)
                               .weight(1f),
                       )
                       IconToggleButton(
                         checked = checkedState.value,
                           onCheckedChange = {
                                checkedState.value = !checkedState.value
                           },
                           modifier = Modifier
                               .padding(end = 2.dp)
                               .size(40.dp)
                       ){
                           Icon(painter = if (checkedState.value){
                               painterResource(id = R.drawable.baseline_favorite_24)
                           }
                           else {
                               painterResource(id = R.drawable.baseline_favorite_border_24)
                                },
                               contentDescription = "favorite",
                               tint = Color(0xFF000066)
                           )
                       }
                   }
                   Row(
                       modifier = Modifier
                           .fillMaxWidth()
                   ) {
                       Icon(
                           painter = painterResource(id = R.drawable.baseline_location_on_24),
                           contentDescription = "location",
                           modifier = Modifier
                               .padding(start = 9.dp)
                               .size(20.dp),
                           tint = Color(0xFF00F0FF)
                       )
                       Text(
                           text = "Magelang",
                           color = Color(0xFF79747E),
                           style = MaterialTheme.typography.labelSmall.copy(
                               fontWeight = FontWeight.Normal,
                               fontStyle = FontStyle.Normal
                           ),
                           modifier = Modifier
                               .padding(top = 1.dp)
                       )
                   }
                   Row(
                       modifier = Modifier
                           .fillMaxWidth()
                           .padding(top = 2.dp)
                   ) {
                       Icon(
                           painter = painterResource(id = R.drawable.baseline_star_24),
                           contentDescription = "location",
                           modifier = Modifier
                               .padding(start = 8.dp)
                               .size(20.dp),
                           tint = Color.Yellow
                       )
                       Text(
                           text = "4 (120)",
                           color = Color(0xFF79747E),
                           style = MaterialTheme.typography.labelSmall.copy(
                               fontWeight = FontWeight.Normal,
                               fontStyle = FontStyle.Normal
                           ),
                           modifier = Modifier
                               .padding(top = 2.dp)
                       )
                   }
               }
           }
       }
    }
}

@Preview(showBackground = true,
    showSystemUi = true,
    device = Devices.PIXEL_4_XL)
@Composable
fun HomePagePreview()
{
    HomePage()
}