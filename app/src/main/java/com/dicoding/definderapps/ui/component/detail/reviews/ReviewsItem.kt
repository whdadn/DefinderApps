package com.dicoding.definderapps.ui.component.detail.reviews

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
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
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.dicoding.definderapps.R

data class Reviews(
    val id: Int,
    val image: Int,
    val name: String,
    val review: String
)

val data = listOf(
    Reviews(
        1,
        R.drawable.borobudur2,
        "Sukuna",
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus ac bibendum nibh. Donec vel ante in tellus lacinia euismod at sit amet nulla. Curabitur sed tincidunt dolor. Fusce egestas est sed dolor faucibus, eget semper mi tempus. Sed nibh ex, ultricies eget sollicitudin vitae, pretium id metus. Interdum et malesuada fames ac ante ipsum primis in faucibus. In lacinia venenatis elit, pulvinar condimentum tortor cursus ut. Pellentesque feugiat metus cursus augue elementum vestibulum. Etiam sit amet erat orci. Curabitur fermentum magna sit amet volutpat blandit. In mattis, nisl in consectetur ullamcorper, magna erat semper nisi, aliquam commodo lectus lectus in purus."
    ),
    Reviews(
        2,
        R.drawable.candi_plaosan,
        "Geto",
        "Ini adalah sebuah candi yang sangat indah dan menakjubkan"
    ),
    Reviews(
        3,
        R.drawable.candi_dieng,
        "Pululu",
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus ac bibendum nibh. Donec vel ante in tellus lacinia euismod at sit amet nulla. Curabitur sed tincidunt dolor. Fusce egestas est sed dolor faucibus, eget semper mi tempus. Sed nibh ex, ultricies eget sollicitudin vitae, pretium id metus. Interdum et malesuada fames ac ante ipsum primis in faucibus. In lacinia venenatis elit, pulvinar condimentum tortor cursus ut. Pellentesque feugiat metus cursus augue elementum vestibulum. Etiam sit amet erat orci. Curabitur fermentum magna sit amet volutpat blandit. In mattis, nisl in consectetur ullamcorper, magna erat semper nisi, aliquam commodo lectus lectus in purus."
    ),
    Reviews(
        4,
        R.drawable.butterfly_photobooth,
        "Uhuy",
        "Ini adalah sebuah candi yang sangat indah dan menakjubkan"
    ),
    Reviews(
        5,
        R.drawable.candi_dieng,
        "Ihiy",
        "Ini adalah sebuah candi yang sangat indah dan menakjubkan"
    ),
    Reviews(
        6,
        R.drawable.definder_register_page,
        "Nobara",
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus ac bibendum nibh. Donec vel ante in tellus lacinia euismod at sit amet nulla. Curabitur sed tincidunt dolor. Fusce egestas est sed dolor faucibus, eget semper mi tempus. Sed nibh ex, ultricies eget sollicitudin vitae, pretium id metus. Interdum et malesuada fames ac ante ipsum primis in faucibus. In lacinia venenatis elit, pulvinar condimentum tortor cursus ut. Pellentesque feugiat metus cursus augue elementum vestibulum. Etiam sit amet erat orci. Curabitur fermentum magna sit amet volutpat blandit. In mattis, nisl in consectetur ullamcorper, magna erat semper nisi, aliquam commodo lectus lectus in purus."
    ),
    Reviews(
        7,
        R.drawable.candi_kalasan,
        "Inumaki",
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus ac bibendum nibh. Donec vel ante in tellus lacinia euismod at sit amet nulla. Curabitur sed tincidunt dolor. Fusce egestas est sed dolor faucibus, eget semper mi tempus. Sed nibh ex, ultricies eget sollicitudin vitae, pretium id metus. Interdum et malesuada fames ac ante ipsum primis in faucibus. In lacinia venenatis elit, pulvinar condimentum tortor cursus ut. Pellentesque feugiat metus cursus augue elementum vestibulum. Etiam sit amet erat orci. Curabitur fermentum magna sit amet volutpat blandit. In mattis, nisl in consectetur ullamcorper, magna erat semper nisi, aliquam commodo lectus lectus in purus."
    ),
    Reviews(
        8,
        R.drawable.candi_sewu,
        "Satoru",
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus ac bibendum nibh. Donec vel ante in tellus lacinia euismod at sit amet nulla. Curabitur sed tincidunt dolor. Fusce egestas est sed dolor faucibus, eget semper mi tempus. Sed nibh ex, ultricies eget sollicitudin vitae, pretium id metus. Interdum et malesuada fames ac ante ipsum primis in faucibus. In lacinia venenatis elit, pulvinar condimentum tortor cursus ut. Pellentesque feugiat metus cursus augue elementum vestibulum. Etiam sit amet erat orci. Curabitur fermentum magna sit amet volutpat blandit. In mattis, nisl in consectetur ullamcorper, magna erat semper nisi, aliquam commodo lectus lectus in purus.\n" +
                "\n" +
                "Maecenas suscipit ac odio eget placerat. Suspendisse potenti. Nullam sit amet ornare felis. Suspendisse suscipit eros a ligula ornare congue. Pellentesque quis egestas nulla, nec sagittis ante. Integer imperdiet elementum quam, at efficitur ipsum fermentum non. Vestibulum fringilla neque arcu, ac euismod risus porttitor a. Interdum et malesuada fames ac ante ipsum primis in faucibus. Vestibulum vestibulum nibh ut elit elementum suscipit. Vestibulum porta arcu et erat rhoncus iaculis. Curabitur luctus odio ac lectus eleifend sodales. Aenean leo felis, rutrum vel lectus non, eleifend bibendum nunc."
    ),
    Reviews(
        9,
        R.drawable.candi_sewu,
        "Megumi",
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus ac bibendum nibh. Donec vel ante in tellus lacinia euismod at sit amet nulla. Curabitur sed tincidunt dolor. Fusce egestas est sed dolor faucibus, eget semper mi tempus. Sed nibh ex, ultricies eget sollicitudin vitae, pretium id metus. Interdum et malesuada fames ac ante ipsum primis in faucibus. In lacinia venenatis elit, pulvinar condimentum tortor cursus ut. Pellentesque feugiat metus cursus augue elementum vestibulum. Etiam sit amet erat orci. Curabitur fermentum magna sit amet volutpat blandit. In mattis, nisl in consectetur ullamcorper, magna erat semper nisi, aliquam commodo lectus lectus in purus.\n" +
                "\n" +
                "Maecenas suscipit ac odio eget placerat. Suspendisse potenti. Nullam sit amet ornare felis. Suspendisse suscipit eros a ligula ornare congue. Pellentesque quis egestas nulla, nec sagittis ante. Integer imperdiet elementum quam, at efficitur ipsum fermentum non. Vestibulum fringilla neque arcu, ac euismod risus porttitor a. Interdum et malesuada fames ac ante ipsum primis in faucibus. Vestibulum vestibulum nibh ut elit elementum suscipit. Vestibulum porta arcu et erat rhoncus iaculis. Curabitur luctus odio ac lectus eleifend sodales. Aenean leo felis, rutrum vel lectus non, eleifend bibendum nunc."
    ),
    Reviews(
        10,
        R.drawable.pantai_pandawa,
        "Yuji",
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus ac bibendum nibh. Donec vel ante in tellus lacinia euismod at sit amet nulla. Curabitur sed tincidunt dolor. Fusce egestas est sed dolor faucibus, eget semper mi tempus. Sed nibh ex, ultricies eget sollicitudin vitae, pretium id metus. Interdum et malesuada fames ac ante ipsum primis in faucibus. In lacinia venenatis elit, pulvinar condimentum tortor cursus ut. Pellentesque feugiat metus cursus augue elementum vestibulum. Etiam sit amet erat orci. Curabitur fermentum magna sit amet volutpat blandit. In mattis, nisl in consectetur ullamcorper, magna erat semper nisi, aliquam commodo lectus lectus in purus.\n" +
                "\n" +
                "Maecenas suscipit ac odio eget placerat. Suspendisse potenti. Nullam sit amet ornare felis. Suspendisse suscipit eros a ligula ornare congue. Pellentesque quis egestas nulla, nec sagittis ante. Integer imperdiet elementum quam, at efficitur ipsum fermentum non. Vestibulum fringilla neque arcu, ac euismod risus porttitor a. Interdum et malesuada fames ac ante ipsum primis in faucibus. Vestibulum vestibulum nibh ut elit elementum suscipit. Vestibulum porta arcu et erat rhoncus iaculis. Curabitur luctus odio ac lectus eleifend sodales. Aenean leo felis, rutrum vel lectus non, eleifend bibendum nunc."
    ),
)

@Composable
fun ReviewsItem(
    reviews: Reviews
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
                    painter = painterResource(id = reviews.image),
                    contentDescription = "profile",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .padding(6.dp)
                        .size(35.dp)
                        .clip(CircleShape),
                )
                Text(
                    text = reviews.name,
                    color = Color(0xFF000080),
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle.Normal
                    )
                )
            }
            Text(
                text = reviews.review,
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

@Composable
fun ReviewsListItem()
{
    var reviewUser by rememberSaveable { mutableStateOf("") }
    var selectedImage by remember { mutableStateOf<List<Uri>>(emptyList()) }
    val multiplePhotoLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickMultipleVisualMedia(),
        onResult = {uris -> selectedImage = uris}
    )


    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(bottom = 64.dp)
        ){
            items(data){list->
                ReviewsItem(reviews = list)
            }
        }

        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
        ) {
            LazyRow(
                contentPadding = PaddingValues(horizontal = 5.dp),
            ){
                items(selectedImage) {uri ->
                    AsyncImage(
                        model = uri,
                        contentDescription = null,
                        modifier = Modifier
                            .width(100.dp)
                            .height(100.dp)
                            .padding(end = 5.dp),
                        contentScale = ContentScale.Crop,
                    )
                }
            }
            
            TextField(
                value = reviewUser,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp),
                placeholder = {
                    Text(
                        text = "Add a review comment...",
                        style = MaterialTheme.typography.labelLarge.copy(
                            fontWeight = FontWeight.Normal,
                            fontStyle = FontStyle.Normal,
                            fontSize = 16.sp
                        ),
                        color = Color(0xFF79747E)
                    )
                },
                onValueChange = {reviewUser = it},
                leadingIcon = {
                    IconButton(
                        onClick = { multiplePhotoLauncher.launch(
                            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                        ) }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.image),
                            contentDescription = "image",
                            tint = Color(0xFF000080)
                        )
                    }
                },
                trailingIcon = {
                    IconButton(
                        onClick = { /*TODO*/ }
                    ) {
                       Icon(
                           imageVector = Icons.Default.Send,
                           contentDescription = "",
                           tint = Color(0xFF000080)
                       )
                    }
                }
            )
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    device = Devices.PIXEL_4_XL
)
@Composable
fun ReviewsItemPreview()
{
    ReviewsListItem()
}