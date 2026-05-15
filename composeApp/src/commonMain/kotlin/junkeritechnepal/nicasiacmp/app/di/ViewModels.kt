package junkeritechnepal.nicasiacmp.app.di

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import org.koin.compose.getKoin
import org.koin.core.module.Module


expect val viewModelModule: Module

@Composable
inline fun<reified T: ViewModel> koinViewModel(): T  {
    val scope = getKoin()
    return viewModel {
        return@viewModel scope.get<T>()
    }
}