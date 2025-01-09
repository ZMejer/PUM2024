package com.example.lista8

import android.app.Application
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun UpdateScreen(arg: String?,onMainScreen: () -> Unit) {
    val viewModel: GradeViewModel = viewModel(
        LocalViewModelStoreOwner.current!!,
        "GradeViewModel",
        GradeViewModelFactory(LocalContext.current.applicationContext as Application)
    )
    Text("LOL")
    Button(onClick = {
        if (arg != null) {
            viewModel.deleteGrade(Grade(arg.toInt(),"",0f))
            onMainScreen()
        }
    }){
        Text("EFIUWIUHEFHIUEFHIUEFW")
    }
}