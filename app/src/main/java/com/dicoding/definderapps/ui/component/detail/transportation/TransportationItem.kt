package com.dicoding.definderapps.ui.component.detail.transportation

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

data class Transportation(
    val id: Int,
    val title: String,
    val price: Int,
    val desc: String
)

val data = listOf(
    Transportation(
        1,
        "Bus",
        5,
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam eget interdum nibh. Pellentesque scelerisque condimentum lorem, sed tempus velit aliquam ultrices. Maecenas non sapien sagittis, mattis arcu vitae, cursus ipsum. Quisque rhoncus ex dignissim consectetur consequat. Vivamus vehicula, dui a tempor aliquet, mi enim pretium nisi, in condimentum nibh odio ut urna. Suspendisse potenti. Fusce id dictum mauris. Duis leo ligula, cursus sit amet urna a, pellentesque sollicitudin orci. In varius dui ac diam dignissim, nec porttitor dui ultricies. Etiam nec magna lacinia, tincidunt nisi eu, lacinia purus. Pellentesque id pulvinar libero. Suspendisse vitae sapien sed augue varius hendrerit. Etiam sit amet est id augue condimentum sodales non nec ante. Quisque augue tellus, rutrum eu urna in, tincidunt tempus velit. Aenean nisl ligula, feugiat vitae ex id, elementum aliquam diam. Praesent mattis fringilla tincidunt."
    ),
    Transportation(
        2,
        "Motor Cycle",
        10,
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam eget interdum nibh. Pellentesque scelerisque condimentum lorem, sed tempus velit aliquam ultrices. Maecenas non sapien sagittis, mattis arcu vitae, cursus ipsum. Quisque rhoncus ex dignissim consectetur consequat. Vivamus vehicula, dui a tempor aliquet, mi enim pretium nisi, in condimentum nibh odio ut urna. Suspendisse potenti. Fusce id dictum mauris. Duis leo ligula, cursus sit amet urna a, pellentesque sollicitudin orci. In varius dui ac diam dignissim, nec porttitor dui ultricies. Etiam nec magna lacinia, tincidunt nisi eu, lacinia purus. Pellentesque id pulvinar libero. Suspendisse vitae sapien sed augue varius hendrerit. Etiam sit amet est id augue condimentum sodales non nec ante. Quisque augue tellus, rutrum eu urna in, tincidunt tempus velit. Aenean nisl ligula, feugiat vitae ex id, elementum aliquam diam. Praesent mattis fringilla tincidunt."
    ),
    Transportation(
        3,
        "Pesawat",
        100,
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam eget interdum nibh. Pellentesque scelerisque condimentum lorem, sed tempus velit aliquam ultrices. Maecenas non sapien sagittis, mattis arcu vitae, cursus ipsum. Quisque rhoncus ex dignissim consectetur consequat. Vivamus vehicula, dui a tempor aliquet, mi enim pretium nisi, in condimentum nibh odio ut urna. Suspendisse potenti. Fusce id dictum mauris. Duis leo ligula, cursus sit amet urna a, pellentesque sollicitudin orci. In varius dui ac diam dignissim, nec porttitor dui ultricies. Etiam nec magna lacinia, tincidunt nisi eu, lacinia purus. Pellentesque id pulvinar libero. Suspendisse vitae sapien sed augue varius hendrerit. Etiam sit amet est id augue condimentum sodales non nec ante. Quisque augue tellus, rutrum eu urna in, tincidunt tempus velit. Aenean nisl ligula, feugiat vitae ex id, elementum aliquam diam. Praesent mattis fringilla tincidunt."
    )
)

@Composable
fun TransportationCard(
    transport: Transportation,
    expanded: Boolean,
    onClickExpanded: (id: Int) -> Unit
) {
    val transition = updateTransition(targetState = expanded, label = null)
    val iconRotation by transition.animateFloat(label = "rotate_icon") { state ->
        if (state)
            180f
        else
            0f
    }
    Column(
        modifier = Modifier
            .padding(top = 16.dp, bottom = 5.dp),
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(15.dp),
            colors = CardDefaults.cardColors(Color.Transparent),
            border = BorderStroke(2.dp, Color(0xFF000080))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = transport.title,
                        color = Color(0xFF000080),
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold,
                            fontStyle = FontStyle.Normal
                        ),
                        modifier = Modifier
                            .weight(6f)
                    )
                    Text(
                        text = "$" + transport.price.toString(),
                        color = Color(0xFF000080),
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold,
                            fontStyle = FontStyle.Normal
                        ),
                    )
                    IconButton(
                        modifier = Modifier
                            .alpha(4f)
                            .weight(1f)
                            .rotate(iconRotation),
                        onClick = { onClickExpanded(transport.id) },
                    ) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowDown,
                            contentDescription = "dropdown_icon",
                            modifier = Modifier
                                .size(35.dp),
                            tint = Color(0xFF000080)
                        )
                    }
                }
                ExpandedTransportationItem(isExpanded = expanded, desc = transport.desc)
            }
        }
    }
}

@Composable
fun ExpandedTransportationItem(
    isExpanded: Boolean,
    desc: String
) {
   if (isExpanded)
   {
       Text(
           text = desc,
           textAlign = TextAlign.Justify,
           color = Color(0xFF00002D),
           modifier = Modifier.padding(top = 16.dp),
           style = MaterialTheme.typography.labelLarge.copy(
               fontWeight = FontWeight.Normal,
               fontStyle = FontStyle.Normal
           )
       )
   }
}

@Composable
fun TransportationListItem() {
    var expandedItem by remember {
        mutableIntStateOf(-1)
    }

    LazyColumn {
        items(data) { list ->
            TransportationCard(
                transport = list,
                expanded = expandedItem == list.id,
                onClickExpanded = { id ->
                    expandedItem = if (expandedItem == id) -1 else id
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
fun TransportationItemPreview() {
    TransportationListItem()
}