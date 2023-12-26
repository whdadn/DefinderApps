package com.dicoding.definderapps.ui.detail.reviews

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dicoding.definderapps.R
import com.dicoding.definderapps.ViewModelFactory
import com.dicoding.definderapps.ui.common.ResultState
import com.dicoding.definderapps.ui.component.detail.reviews.ReviewsItem
import com.dicoding.definderapps.ui.detail.DetailViewModel
import kotlinx.coroutines.launch


@Composable
fun ReviewsScreen(
    token: String,
    placeId: Int,
    context: Context = LocalContext.current,
    viewModel: DetailViewModel = viewModel(factory = ViewModelFactory.getInstance(context)),
) {
    var reviewUser by rememberSaveable { mutableStateOf("") }
    var showLoading by rememberSaveable { mutableStateOf(false) }
    var createReview by rememberSaveable { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    Box(modifier = Modifier.fillMaxSize()) {
        if (showLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
        viewModel.dataReviewPlace.collectAsState(initial = ResultState.Loading).value.let { get ->
            when (get) {
                is ResultState.Loading -> {
                    showLoading = true
                    viewModel.getReviewPlace(token, placeId)
                }

                is ResultState.Success -> {
                    showLoading = false

                    LazyColumn(
                        modifier = Modifier
                            .padding(bottom = 64.dp)
                    ) {
                        if (!get.data.data.reviews.isNullOrEmpty()){
                            items(get.data.data.reviews, key = {it?.review.toString()}){
                                ReviewsItem(
                                    name = it?.userName.toString(),
                                    review = it?.review.toString()
                                )
                            }

                        }

                    }

                }
                is ResultState.Error -> {
                    showLoading = false
                    Toast.makeText(context, get.error, Toast.LENGTH_SHORT).show()
                }

            }
        }


        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
        ) {
            TextField(
                value = reviewUser,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp),
                placeholder = {
                    Text(
                        text = stringResource(R.string.add_eview_comment),
                        style = MaterialTheme.typography.labelLarge.copy(
                            fontWeight = FontWeight.Normal,
                            fontStyle = FontStyle.Normal,
                            fontSize = 16.sp
                        ),
                        color = MaterialTheme.colorScheme.outline
                    )
                },
                onValueChange = { reviewUser = it },
                trailingIcon = {
                    IconButton(
                        onClick = {
                            if (reviewUser!=""){
                                scope.launch {
                                    viewModel.createReview(token,placeId,reviewUser).asFlow()
                                        .collect{review->
                                            when(review){
                                                is ResultState.Loading -> {
                                                    showLoading = true
                                                }
                                                is ResultState.Success -> {
                                                    createReview=true
                                                    Toast.makeText(context, context.getString(R.string.successfully_added_review), Toast.LENGTH_SHORT).show()
                                                    reviewUser = ""
                                                }

                                                is ResultState.Error -> {
                                                    showLoading = false
                                                    Toast.makeText(context, context.getString(R.string.failed_to_add_review), Toast.LENGTH_SHORT).show()
                                                }
                                            }
                                        }
                                }
                            }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Send,
                            contentDescription = "",
                            tint = MaterialTheme.colorScheme.onTertiaryContainer
                        )
                    }
                    LaunchedEffect(createReview){
                        viewModel.getReviewPlace(token,placeId)
                        createReview=false
                        showLoading=false
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
fun ReviewsItemPreview() {
    ReviewsScreen(token="", placeId = 1)
}
