package com.dicoding.definderapps.ui.detail.about

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dicoding.definderapps.R

@Composable
fun AboutScreen(
    aboutDestination:String
)
{
    LazyColumn{
        item {
            Column(
                modifier = Modifier
                    .padding(top = 16.dp)
            ) {
                Text(
                    text = stringResource(R.string.about_title),
                    color = Color(0xFF00002D),
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle.Normal
                    )
                )
                Text(
                    text = aboutDestination,
                    color = Color(0xFF00002D),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Normal,
                        fontStyle = FontStyle.Normal
                    ),
                    modifier= Modifier.padding(top=4.dp)
                )
            }
        }
    }
}
@Preview(
    showBackground = true
)
@Composable
fun AboutScreenPreview()
{
    AboutScreen(aboutDestination = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus ac bibendum nibh. Donec vel ante in tellus lacinia euismod at sit amet nulla. Curabitur sed tincidunt dolor. Fusce egestas est sed dolor faucibus, eget semper mi tempus. Sed nibh ex, ultricies eget sollicitudin vitae, pretium id metus. Interdum et malesuada fames ac ante ipsum primis in faucibus. In lacinia venenatis elit, pulvinar condimentum tortor cursus ut. Pellentesque feugiat metus cursus augue elementum vestibulum. Etiam sit amet erat orci. Curabitur fermentum magna sit amet volutpat blandit. In mattis, nisl in consectetur ullamcorper, magna erat semper nisi, aliquam commodo lectus lectus in purus.")
}