package com.dicoding.definderapps.ui.component.detail.about

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun AboutPage()
{
    LazyColumn{
        item {
            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Text(
                    text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam eget interdum nibh. Pellentesque scelerisque condimentum lorem, sed tempus velit aliquam ultrices. Maecenas non sapien sagittis, mattis arcu vitae, cursus ipsum. Quisque rhoncus ex dignissim consectetur consequat. Vivamus vehicula, dui a tempor aliquet, mi enim pretium nisi, in condimentum nibh odio ut urna. Suspendisse potenti. Fusce id dictum mauris. Duis leo ligula, cursus sit amet urna a, pellentesque sollicitudin orci. In varius dui ac diam dignissim, nec porttitor dui ultricies. Etiam nec magna lacinia, tincidunt nisi eu, lacinia purus. Pellentesque id pulvinar libero. Suspendisse vitae sapien sed augue varius hendrerit. Etiam sit amet est id augue condimentum sodales non nec ante. Quisque augue tellus, rutrum eu urna in, tincidunt tempus velit. Aenean nisl ligula, feugiat vitae ex id, elementum aliquam diam. Praesent mattis fringilla tincidunt.\n" +
                            "\n" +
                            "Praesent ullamcorper ex felis, sed congue lorem rhoncus tincidunt. Suspendisse potenti. Sed sodales turpis lectus, eu scelerisque lorem aliquet quis. Suspendisse non fringilla metus. Mauris ligula sem, congue id erat sit amet, aliquet finibus mi. Donec ac quam et lorem varius bibendum. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Vestibulum vitae justo vitae turpis rhoncus porta. Fusce augue sem, pretium et varius et, maximus venenatis magna. Quisque fermentum iaculis lorem id condimentum. Suspendisse volutpat tellus et felis auctor, id aliquet velit gravida. Phasellus et mauris ut magna congue ultricies. Etiam at iaculis nunc, at pulvinar turpis. Donec ut sodales turpis. Aenean consequat felis dui, ut pretium lacus maximus elementum.\n" +
                            "\n" +
                            "Integer ut elit id nisl pretium vulputate vel a ipsum. In at congue nibh. Nam auctor porttitor purus. Donec tempus facilisis ligula, et suscipit odio laoreet vel. Phasellus varius varius dictum. Morbi non urna diam. Curabitur tristique ante eu magna ultricies elementum. Etiam magna lorem, posuere quis venenatis nec, vehicula eget est. Vivamus volutpat eleifend sapien, eu luctus sem elementum vel.\n" +
                            "\n" +
                            "Sed maximus a ipsum eget aliquam. Nullam massa eros, ultricies auctor gravida id, condimentum in arcu. Integer eu est ut orci fringilla malesuada eu at elit. Phasellus maximus et massa molestie rhoncus. Mauris ac felis laoreet, egestas purus in, congue magna. Curabitur posuere ligula ac maximus dictum. Ut fermentum sagittis suscipit. Nam iaculis mauris vel enim tristique, eu posuere justo consequat. Etiam sit amet turpis felis. Aenean convallis, mauris quis porta eleifend, sapien leo rhoncus nisl, ut tristique metus risus in ligula.\n" +
                            "\n" +
                            "Aenean eleifend nunc sit amet pellentesque volutpat. Nulla purus turpis, efficitur ac sagittis id, commodo eget ex. Integer eget ullamcorper metus. Aenean non nisl sagittis risus rhoncus lacinia. Aenean laoreet lorem at laoreet iaculis. Nunc a interdum mauris. Duis finibus, ipsum eu suscipit hendrerit, orci odio porttitor ex, id congue nunc mi vitae sapien. Donec condimentum purus lobortis urna rutrum sagittis eu accumsan purus. Cras odio orci, interdum vel varius et, faucibus ut lacus. Sed et tempor felis, eget consequat risus.",
                    color = Color(0xFF00002D),
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Normal,
                        fontStyle = FontStyle.Normal
                    )
                )
            }
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
fun AboutPagePreview()
{
    AboutPage()
}