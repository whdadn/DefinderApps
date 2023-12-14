package com.dicoding.definderapps.ui.detail.transportation

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.dicoding.definderapps.R
import com.dicoding.definderapps.ui.component.detail.transportation.DetailTransportationItem

data class Reviews(
    val id: Int,
    val image: Int,
    val name: String,
    val transportationName: String,
    val transportationDesc: String
)

val data = listOf(
    Reviews(
        1,
        R.drawable.borobudur2,
        "Sukuna",
        "Motor Cycle",
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus ac bibendum nibh. Donec vel ante in tellus lacinia euismod at sit amet nulla. Curabitur sed tincidunt dolor. Fusce egestas est sed dolor faucibus, eget semper mi tempus. Sed nibh ex, ultricies eget sollicitudin vitae, pretium id metus. Interdum et malesuada fames ac ante ipsum primis in faucibus. In lacinia venenatis elit, pulvinar condimentum tortor cursus ut. Pellentesque feugiat metus cursus augue elementum vestibulum. Etiam sit amet erat orci. Curabitur fermentum magna sit amet volutpat blandit. In mattis, nisl in consectetur ullamcorper, magna erat semper nisi, aliquam commodo lectus lectus in purus."
    ),
    Reviews(
        2,
        R.drawable.candi_plaosan,
        "Geto",
        "Bus",
        "Ini adalah sebuah candi yang sangat indah dan menakjubkan"
    ),
    Reviews(
        3,
        R.drawable.candi_dieng,
        "Pululu",
        "Car",
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus ac bibendum nibh. Donec vel ante in tellus lacinia euismod at sit amet nulla. Curabitur sed tincidunt dolor. Fusce egestas est sed dolor faucibus, eget semper mi tempus. Sed nibh ex, ultricies eget sollicitudin vitae, pretium id metus. Interdum et malesuada fames ac ante ipsum primis in faucibus. In lacinia venenatis elit, pulvinar condimentum tortor cursus ut. Pellentesque feugiat metus cursus augue elementum vestibulum. Etiam sit amet erat orci. Curabitur fermentum magna sit amet volutpat blandit. In mattis, nisl in consectetur ullamcorper, magna erat semper nisi, aliquam commodo lectus lectus in purus."
    ),
    Reviews(
        4,
        R.drawable.butterfly_photobooth,
        "Uhuy",
        "Trolly",
        "Ini adalah sebuah candi yang sangat indah dan menakjubkan"
    ),
    Reviews(
        5,
        R.drawable.candi_dieng,
        "Ihiy",
        "Motor",
        "Ini adalah sebuah candi yang sangat indah dan menakjubkan"
    ),
    Reviews(
        6,
        R.drawable.definder_register_page,
        "Nobara",
        "Walk",
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus ac bibendum nibh. Donec vel ante in tellus lacinia euismod at sit amet nulla. Curabitur sed tincidunt dolor. Fusce egestas est sed dolor faucibus, eget semper mi tempus. Sed nibh ex, ultricies eget sollicitudin vitae, pretium id metus. Interdum et malesuada fames ac ante ipsum primis in faucibus. In lacinia venenatis elit, pulvinar condimentum tortor cursus ut. Pellentesque feugiat metus cursus augue elementum vestibulum. Etiam sit amet erat orci. Curabitur fermentum magna sit amet volutpat blandit. In mattis, nisl in consectetur ullamcorper, magna erat semper nisi, aliquam commodo lectus lectus in purus."
    ),
    Reviews(
        7,
        R.drawable.candi_kalasan,
        "Inumaki",
        "Car",
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus ac bibendum nibh. Donec vel ante in tellus lacinia euismod at sit amet nulla. Curabitur sed tincidunt dolor. Fusce egestas est sed dolor faucibus, eget semper mi tempus. Sed nibh ex, ultricies eget sollicitudin vitae, pretium id metus. Interdum et malesuada fames ac ante ipsum primis in faucibus. In lacinia venenatis elit, pulvinar condimentum tortor cursus ut. Pellentesque feugiat metus cursus augue elementum vestibulum. Etiam sit amet erat orci. Curabitur fermentum magna sit amet volutpat blandit. In mattis, nisl in consectetur ullamcorper, magna erat semper nisi, aliquam commodo lectus lectus in purus."
    ),
    Reviews(
        8,
        R.drawable.candi_sewu,
        "Satoru",
        "Truck",
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus ac bibendum nibh. Donec vel ante in tellus lacinia euismod at sit amet nulla. Curabitur sed tincidunt dolor. Fusce egestas est sed dolor faucibus, eget semper mi tempus. Sed nibh ex, ultricies eget sollicitudin vitae, pretium id metus. Interdum et malesuada fames ac ante ipsum primis in faucibus. In lacinia venenatis elit, pulvinar condimentum tortor cursus ut. Pellentesque feugiat metus cursus augue elementum vestibulum. Etiam sit amet erat orci. Curabitur fermentum magna sit amet volutpat blandit. In mattis, nisl in consectetur ullamcorper, magna erat semper nisi, aliquam commodo lectus lectus in purus.\n" +
                "\n" +
                "Maecenas suscipit ac odio eget placerat. Suspendisse potenti. Nullam sit amet ornare felis. Suspendisse suscipit eros a ligula ornare congue. Pellentesque quis egestas nulla, nec sagittis ante. Integer imperdiet elementum quam, at efficitur ipsum fermentum non. Vestibulum fringilla neque arcu, ac euismod risus porttitor a. Interdum et malesuada fames ac ante ipsum primis in faucibus. Vestibulum vestibulum nibh ut elit elementum suscipit. Vestibulum porta arcu et erat rhoncus iaculis. Curabitur luctus odio ac lectus eleifend sodales. Aenean leo felis, rutrum vel lectus non, eleifend bibendum nunc."
    ),
    Reviews(
        9,
        R.drawable.candi_sewu,
        "Megumi",
        "Car",
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus ac bibendum nibh. Donec vel ante in tellus lacinia euismod at sit amet nulla. Curabitur sed tincidunt dolor. Fusce egestas est sed dolor faucibus, eget semper mi tempus. Sed nibh ex, ultricies eget sollicitudin vitae, pretium id metus. Interdum et malesuada fames ac ante ipsum primis in faucibus. In lacinia venenatis elit, pulvinar condimentum tortor cursus ut. Pellentesque feugiat metus cursus augue elementum vestibulum. Etiam sit amet erat orci. Curabitur fermentum magna sit amet volutpat blandit. In mattis, nisl in consectetur ullamcorper, magna erat semper nisi, aliquam commodo lectus lectus in purus.\n" +
                "\n" +
                "Maecenas suscipit ac odio eget placerat. Suspendisse potenti. Nullam sit amet ornare felis. Suspendisse suscipit eros a ligula ornare congue. Pellentesque quis egestas nulla, nec sagittis ante. Integer imperdiet elementum quam, at efficitur ipsum fermentum non. Vestibulum fringilla neque arcu, ac euismod risus porttitor a. Interdum et malesuada fames ac ante ipsum primis in faucibus. Vestibulum vestibulum nibh ut elit elementum suscipit. Vestibulum porta arcu et erat rhoncus iaculis. Curabitur luctus odio ac lectus eleifend sodales. Aenean leo felis, rutrum vel lectus non, eleifend bibendum nunc."
    ),
    Reviews(
        10,
        R.drawable.pantai_pandawa,
        "Yuji",
        "Bus",
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus ac bibendum nibh. Donec vel ante in tellus lacinia euismod at sit amet nulla. Curabitur sed tincidunt dolor. Fusce egestas est sed dolor faucibus, eget semper mi tempus. Sed nibh ex, ultricies eget sollicitudin vitae, pretium id metus. Interdum et malesuada fames ac ante ipsum primis in faucibus. In lacinia venenatis elit, pulvinar condimentum tortor cursus ut. Pellentesque feugiat metus cursus augue elementum vestibulum. Etiam sit amet erat orci. Curabitur fermentum magna sit amet volutpat blandit. In mattis, nisl in consectetur ullamcorper, magna erat semper nisi, aliquam commodo lectus lectus in purus.\n" +
                "\n" +
                "Maecenas suscipit ac odio eget placerat. Suspendisse potenti. Nullam sit amet ornare felis. Suspendisse suscipit eros a ligula ornare congue. Pellentesque quis egestas nulla, nec sagittis ante. Integer imperdiet elementum quam, at efficitur ipsum fermentum non. Vestibulum fringilla neque arcu, ac euismod risus porttitor a. Interdum et malesuada fames ac ante ipsum primis in faucibus. Vestibulum vestibulum nibh ut elit elementum suscipit. Vestibulum porta arcu et erat rhoncus iaculis. Curabitur luctus odio ac lectus eleifend sodales. Aenean leo felis, rutrum vel lectus non, eleifend bibendum nunc."
    ),
)
@Composable
fun DetailTransportationScreen()
{
   LazyColumn{
       items(data){list ->
           DetailTransportationItem(
               name = list.name,
               image = list.image,
               transportationName = list.transportationName,
               transportationDesc = list.transportationDesc
           )
       }
   }
}

@Preview(
    showBackground = true
)
@Composable
fun DetailTransportationScreenPreview()
{
    DetailTransportationScreen()
}