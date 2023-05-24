package pa.ac.utp.components.listaestudiantes.data.repository

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pa.ac.utp.components.listaestudiantes.data.StudentPreferences
import pa.ac.utp.components.listaestudiantes.data.dao.StudentDao
import pa.ac.utp.components.listaestudiantes.data.databasemodel.Student
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StudentRepository @Inject constructor(
    @ApplicationContext val context: Context,
    private val studentDao: StudentDao,
    private val studentPreferences: StudentPreferences
) {
    suspend fun getAllStudents(): List<Student> = withContext(Dispatchers.IO) {
        return@withContext studentDao.getAllStudents()
    }

    suspend fun canEatInClass(): Boolean = withContext(Dispatchers.IO) {
        return@withContext studentPreferences.eatInClass
    }

    suspend fun eatInClass(value: Boolean) = withContext(Dispatchers.IO) {
        studentPreferences.eatInClass = value
    }

    suspend fun addStudent(student: Student) = withContext(Dispatchers.IO) {
        studentDao.insertStudent(student)
    }

    suspend fun deleteStudent(student: Student) = withContext(Dispatchers.IO) {
        studentDao.deleteStudent(student)
    }
}
