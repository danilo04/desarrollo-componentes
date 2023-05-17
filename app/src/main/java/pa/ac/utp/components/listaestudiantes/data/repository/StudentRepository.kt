package pa.ac.utp.components.listaestudiantes.data.repository

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import pa.ac.utp.components.listaestudiantes.R
import pa.ac.utp.components.listaestudiantes.StudentListApplication
import pa.ac.utp.components.listaestudiantes.data.StudentPreferences
import pa.ac.utp.components.listaestudiantes.data.model.Student
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StudentRepository @Inject constructor(
    @ApplicationContext val context: Context,
    private val studentPreferences: StudentPreferences
) {
    suspend fun getAllStudents(): List<Student> = withContext(Dispatchers.IO) {
        delay(1000)

        return@withContext context
            .openFileInput(StudentListApplication.STUDENT_LIST_FILE)
            .bufferedReader()
            .useLines { lines: Sequence<String> ->
                lines.map { line ->
                    val parts = line.split(",")
                    val image = if (parts[3] == "1") {
                        R.drawable.student1
                    } else {
                        R.drawable.student2
                    }
                    Student(
                        name = parts[0],
                        lastname = parts[1],
                        idNumber = parts[2],
                        image = image
                    )
                }.toList().sortedBy { it.name }
        }
    }

    suspend fun canEatInClass(): Boolean = withContext(Dispatchers.IO) {
        return@withContext studentPreferences.eatInClass
    }

    suspend fun eatInClass(value: Boolean) = withContext(Dispatchers.IO) {
        studentPreferences.eatInClass = value
    }

    suspend fun addStudent(student: Student) = withContext(Dispatchers.IO) {
        context.openFileOutput(StudentListApplication.STUDENT_LIST_FILE, Context.MODE_APPEND).use {
            it.write("${student.name},${student.lastname},${student.idNumber},2\n".toByteArray())
        }
    }
}
