package pa.ac.utp.components.listaestudiantes.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import pa.ac.utp.components.listaestudiantes.data.model.Student
import pa.ac.utp.components.listaestudiantes.data.repository.StudentRepository
import javax.inject.Inject

@HiltViewModel
class StudentViewModel @Inject constructor(
    private val studentRepository: StudentRepository
) : ViewModel() {
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

    fun addStudent(student: Student) {
        viewModelScope.launch {
            studentRepository.addStudent(student)
            val students = studentRepository.getAllStudents()
            _uiState.value = UiState.StudentList(students)
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
