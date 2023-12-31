package com.dicoding.definderapps.ui.detail.transportation

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dicoding.definderapps.R
import com.dicoding.definderapps.ViewModelFactory
import com.dicoding.definderapps.ui.common.ResultState
import com.dicoding.definderapps.ui.detail.DetailViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AddTransportation(
    modifier: Modifier = Modifier,
    closeDialog: () -> Unit,
    idDestination: Int,
    context: Context = LocalContext.current,
    viewModel: DetailViewModel = viewModel(factory=ViewModelFactory.getInstance(context))
) {

    val dataType = mapOf(
        "land" to stringResource(R.string.transport_type_1),
        "air" to stringResource(R.string.transport_type_2),
        "sea" to stringResource(R.string.transport_type_3)
    )
    var selectedOption by rememberSaveable { mutableStateOf("") }
    var valueOption by rememberSaveable { mutableStateOf("") }
    var nameTransportation by rememberSaveable { mutableStateOf("") }
    var explainFlow by rememberSaveable { mutableStateOf("") }
    var showLoading by rememberSaveable { mutableStateOf(false) }

    Dialog(
        onDismissRequest = {
            closeDialog()
        },
        properties = DialogProperties(
            usePlatformDefaultWidth = false // experimental
        )
    ) {
        val scope = rememberCoroutineScope()
        val snackbarHostState = remember { SnackbarHostState() }

        Scaffold(
            snackbarHost = {
                SnackbarHost(
                    hostState = snackbarHostState,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
            }
        ) {
            Surface(modifier = Modifier.fillMaxSize()) {
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    if(showLoading){
                        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                    }
                    LazyColumn(
                        contentPadding = PaddingValues(start = 16.dp, end = 16.dp, bottom = 16.dp),
                        modifier = modifier
                            .fillMaxSize()
                            .padding(vertical = 16.dp)
                            .background(
                                MaterialTheme.colorScheme.background,
                                RoundedCornerShape(7.dp)
                            )
                    ) {
                        stickyHeader {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(MaterialTheme.colorScheme.background)
                            ) {
                                Box(modifier = Modifier.fillMaxWidth()) {
                                    Text(
                                        text = stringResource(R.string.add_transportation),
                                        style = MaterialTheme.typography.titleMedium.copy(
                                            fontWeight = FontWeight.Bold,
                                            fontStyle = FontStyle.Normal
                                        ),
                                        color = MaterialTheme.colorScheme.onSurface,
                                        modifier = Modifier.align(Alignment.Center)
                                    )
                                    IconButton(
                                        onClick = closeDialog,
                                        modifier = Modifier.align(Alignment.CenterEnd)
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Close,
                                            contentDescription = stringResource(R.string.close_add_transportation)
                                        )
                                    }
                                }
                            }
                        }
                        item {
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 20.dp),
                                colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primaryContainer),
                            ) {
                                Column(modifier = Modifier.padding(8.dp)) {
                                    Text(
                                        text = stringResource(R.string.add_transport_type),
                                        color = MaterialTheme.colorScheme.onTertiaryContainer,
                                        style = MaterialTheme.typography.bodyMedium.copy(
                                            fontWeight = FontWeight.Normal,
                                            fontStyle = FontStyle.Normal
                                        ),
                                        modifier = Modifier
                                            .padding(7.dp)
                                    )
                                    for ((key, value) in dataType) {
                                        Row(
                                            verticalAlignment = Alignment.CenterVertically,
                                            modifier = Modifier
                                                .selectable(
                                                    selected = selectedOption == key,
                                                    onClick = {
                                                        selectedOption = key
                                                        valueOption = value
                                                    }
                                                ),
                                        ) {
                                            RadioButton(
                                                selected = selectedOption == key,
                                                onClick = {
                                                    selectedOption = key
                                                    valueOption = value
                                                },
                                                colors = RadioButtonDefaults.colors(MaterialTheme.colorScheme.secondary)
                                            )
                                            Text(
                                                text = value,
                                                color = MaterialTheme.colorScheme.onTertiaryContainer,
                                                style = MaterialTheme.typography.bodyMedium.copy(
                                                    fontWeight = FontWeight.Normal,
                                                    fontStyle = FontStyle.Normal
                                                )
                                            )
                                        }
                                    }
                                }
                            }
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 20.dp),
                                colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primaryContainer),
                            ) {
                                Column(modifier = Modifier.padding(8.dp)) {
                                    Text(
                                        text = stringResource(R.string.add_transport_name),
                                        color = MaterialTheme.colorScheme.onTertiaryContainer,
                                        style = MaterialTheme.typography.bodyMedium.copy(
                                            fontWeight = FontWeight.Normal,
                                            fontStyle = FontStyle.Normal
                                        ),
                                        modifier = Modifier
                                            .padding(7.dp)
                                    )
                                    OutlinedTextField(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(
                                                top = 16.dp,
                                                bottom = 8.dp,
                                                start = 8.dp,
                                                end = 8.dp
                                            ),
                                        value = nameTransportation,
                                        colors = OutlinedTextFieldDefaults.colors(
                                            focusedBorderColor = MaterialTheme.colorScheme.primary,
                                            unfocusedBorderColor = MaterialTheme.colorScheme.outline,
                                        ),
                                        textStyle = TextStyle.Default.copy(
                                            fontSize = 16.sp
                                        ),
                                        onValueChange = { nameTransportation = it },
                                        maxLines = 1,
                                        singleLine = true,
                                        shape = RoundedCornerShape(7.dp)
                                    )
                                }
                            }
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 20.dp),
                                colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primaryContainer),
                            ) {
                                Column(modifier = Modifier.padding(8.dp)) {
                                    Text(
                                        text = stringResource(R.string.add_transport_explain_flow),
                                        color = MaterialTheme.colorScheme.onTertiaryContainer,
                                        style = MaterialTheme.typography.bodyMedium.copy(
                                            fontWeight = FontWeight.Normal,
                                            fontStyle = FontStyle.Normal
                                        ),
                                        modifier = Modifier
                                            .padding(7.dp)
                                    )
                                    OutlinedTextField(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .wrapContentHeight()
                                            .padding(
                                                top = 16.dp,
                                                bottom = 8.dp,
                                                start = 8.dp,
                                                end = 8.dp
                                            ),
                                        value = explainFlow,
                                        colors = OutlinedTextFieldDefaults.colors(
                                            focusedBorderColor = MaterialTheme.colorScheme.primary,
                                            unfocusedBorderColor = MaterialTheme.colorScheme.outline,
                                        ),
                                        textStyle = TextStyle.Default.copy(
                                            fontSize = 16.sp
                                        ),
                                        onValueChange = { explainFlow = it },
                                        maxLines = 5,
                                        shape = RoundedCornerShape(7.dp)
                                    )
                                }
                            }
                            val session by viewModel.getSession().observeAsState()
                            Button(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                onClick = {
                                    val token = session?.token.toString()
                                    val userId = session?.id.toString().trim().toInt()
                                    if (selectedOption != "" && nameTransportation != "" && explainFlow != "") {
                                        scope.launch {
                                            viewModel.addTransport(token,idDestination,userId,selectedOption,nameTransportation,explainFlow).asFlow()
                                                .collect{transport->
                                                    when(transport){
                                                        is ResultState.Loading -> {
                                                            showLoading = true
                                                        }
                                                        is ResultState.Success -> {
                                                            closeDialog()
                                                        }

                                                        is ResultState.Error -> {
                                                            showLoading = false
                                                            snackbarHostState.showSnackbar(
                                                                message = transport.error,
                                                                withDismissAction = true,
                                                                duration = SnackbarDuration.Short,
                                                            )
                                                        }
                                                    }
                                                }
                                        }

                                    }else{
                                        scope.launch {
                                            snackbarHostState.showSnackbar(
                                                message = context.getString(R.string.error_field_empty),
                                                withDismissAction = false,
                                                duration = SnackbarDuration.Short,
                                            )
                                        }
                                    }
                                },
                                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
                                shape = RoundedCornerShape(4.dp)
                            ) {
                                Text(
                                    modifier = Modifier.padding(vertical = 4.dp),
                                    text = stringResource(id = R.string.submit),
                                    color = MaterialTheme.colorScheme.onPrimary,
                                    style = MaterialTheme.typography.bodyLarge.copy(
                                        fontWeight = FontWeight.Normal,
                                        fontStyle = FontStyle.Normal
                                    )
                                )
                            }
                        }
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
fun AddTransportationPreview() {
    AddTransportation(closeDialog = {  }, idDestination =1 )
}