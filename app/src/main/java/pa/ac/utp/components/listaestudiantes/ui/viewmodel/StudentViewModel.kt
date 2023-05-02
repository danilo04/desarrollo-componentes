package pa.ac.utp.components.listaestudiantes.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keenencharles.unsplash.Unsplash
import com.keenencharles.unsplash.api.Scope
import com.keenencharles.unsplash.api.UnsplashResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import pa.ac.utp.components.listaestudiantes.data.model.Student
import pa.ac.utp.components.listaestudiantes.data.repository.StudentRepository

class StudentViewModel : ViewModel() {
    private val studentRepository = StudentRepository()

    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()


    init {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            val students = studentRepository.getAllStudents()
            _uiState.value = UiState.StudentList(students)
        }
    }

    fun onClick(index: Int) {
        if (_uiState.value is UiState.StudentList) {
            _uiState.value = (_uiState.value as UiState.StudentList).copy(selected = index)
        }
    }

    sealed interface UiState {
        object Loading : UiState
        data class StudentList(
            val students: List<Student>,
            val selected: Int? = null
        ) : UiState
    }
}
