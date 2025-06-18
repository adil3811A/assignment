@file:OptIn(ExperimentalMaterial3Api::class)

package com.inovantsolutions.assignment.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.inovantsolutions.assignment.presentation.theme.AssignmentTheme

@Composable
fun MainApp(
    modifier: Modifier = Modifier
) {
    val  viewModel = viewModel<ViewModel>()
    val state = viewModel.uiSate.collectAsState<UiSate>()
    SideEffect {
        viewModel.getData()
    }
    AssignmentTheme {
        when(state.value){
            is UiSate.Error -> {
                Box (
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ){
                    Text("error is ${(state.value as UiSate.Error).message}")
                }
            }
            UiSate.Idea -> {
                Box (
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ){
                    Text("Nothing is wrong")
                }
            }
            UiSate.Loading -> {
                Box (
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ){
                    CircularProgressIndicator()
                }
            }
            is UiSate.Sucsess -> {
                ResponseUi((state.value as UiSate.Sucsess).response)
            }
        }
    }
}

