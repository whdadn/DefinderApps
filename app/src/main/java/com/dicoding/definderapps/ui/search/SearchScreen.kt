package com.dicoding.definderapps.ui.search

import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dicoding.definderapps.R
import com.dicoding.definderapps.ViewModelFactory
import com.dicoding.definderapps.ui.component.home.DestinationItem
import com.dicoding.definderapps.ui.component.home.SearchBar

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    viewModel: SearchViewModel = viewModel(factory = ViewModelFactory.getInstance(LocalContext.current))
) {
    val destinationWithImage by viewModel.getDestinationWithImage.collectAsState()
    val query by viewModel.query
    var expanded by remember { mutableStateOf(false) }
    var option by remember { mutableStateOf("") }

    Box(modifier = Modifier) {
        val listState = rememberLazyListState()

        LazyColumn(
            state = listState,
            contentPadding = PaddingValues(bottom = 10.dp)
        ) {
            stickyHeader {
                Column(
                    modifier = Modifier.background(MaterialTheme.colorScheme.background)
                ) {
                    SearchBar(
                        query = query,
                        onQueryChange = viewModel::search,
                        modifier = Modifier
                            .padding(vertical = 16.dp, horizontal = 16.dp)
                    )

                    ExposedDropdownMenuBox(
                        expanded = expanded,
                        onExpandedChange = {expanded = it},
                        modifier = Modifier
                            .width(155.dp)
                    ) {
                        TextField(
                            value = option,
                            onValueChange = {},
                            readOnly = true,
                            leadingIcon = {
                                Icon(
                                    painter = painterResource(id = R.drawable.filter),
                                    contentDescription = "filter",
                                    modifier = Modifier
                                        .size(20.dp),
                                )
                            },
                            modifier = Modifier
                                .menuAnchor()
                                .padding(start = 16.dp)
                                .border(
                                    BorderStroke(2.dp, Color(0xFF000080)),
                                    shape = RoundedCornerShape(10.dp)
                                ),
                            colors = TextFieldDefaults.colors(
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,
                                focusedContainerColor = Color.Transparent,
                                unfocusedContainerColor = Color.Transparent
                            ),
                            placeholder = {
                                Text(
                                    text = "Filter",
                                    color = Color(0xFF000080),
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }
                        )
                        ExposedDropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false },
                            modifier = Modifier
                                .padding(start = 16.dp)
                        ) {
                            DropdownMenuItem(
                                text = { Text(text = "Tourism") },
                                onClick = {
                                    option = "Tourism"
                                    expanded = false
                                },
                            )
                            DropdownMenuItem(
                                text = { Text(text = "Tour Guide") },
                                onClick = {
                                    option = "Tour Guide"
                                    expanded = false
                                },
                            )
                        }
                    }
                }
            }



            if (query.isNotEmpty()) {
                items(destinationWithImage, key = { it.destination.id }) { data ->
                    Column(
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp)
                    ) {
                        DestinationItem(
                            name = data.destination.name,
                            imageUrl = data.imageDestination.first().imageUrl,
                            location = data.destination.location,
                            rating = data.destination.rating,
                            favorite = data.destination.favorited,
                            favoriteChange = {
                                if (data.destination.favorited) {
                                    viewModel.setFavorited(data.destination.id, false)
                                } else {
                                    viewModel.setFavorited(data.destination.id, true)
                                }
                            },
                            modifier = Modifier
                                .animateItemPlacement(tween(durationMillis = 100))
                                .clickable { }
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
fun SearchScreenPreview() {
    SearchScreen()
}