package pa.ac.utp.components.listaestudiantes.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import pa.ac.utp.components.listaestudiantes.R
import pa.ac.utp.components.listaestudiantes.data.model.Student
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StudentRepository @Inject constructor() {
    suspend fun getAllStudents(): List<Student> = withContext(Dispatchers.IO) {
        delay(1000)
        return@withContext listOf(
            Student(name = "Laury", lastname = "Arenales", idNumber = "4-xx-xx", image = R.drawable.student1),
            Student(name = "Mel", lastname = "Smith", idNumber = "METO-764-1881", image = R.drawable.student2),
            Student(name = "Jose", lastname = "Arenales", idNumber = "6-xx-xx", image = R.drawable.student1),
            Student(name = "Rumi", lastname = "Arenales", idNumber = "4-xx-xx", image = R.drawable.student2),
            Student(name = "Nila", lastname = "Arenales", idNumber = "4-xx-xx", image = R.drawable.student2),
            Student(name = "Joseph", lastname = "Arenales", idNumber = "4-xx-xx", image = R.drawable.student2),
            Student(name = "Cristian", lastname = "Arenales", idNumber = "2-xx-xx", image = R.drawable.student2),
            Student(name = "Dimas", lastname = "Arenales", idNumber = "4-xx-xx", image = R.drawable.student1),
            Student(name = "Ivonne", lastname = "Arenales", idNumber = "4-xx-xx", image = R.drawable.student2),
            Student(name = "Ivonne", lastname = "Arenales", idNumber = "4-xx-xx", image = R.drawable.student2),
            Student(name = "Ivonne", lastname = "Arenales", idNumber = "4-xx-xx", image = R.drawable.student2),
            Student(name = "Ivonne", lastname = "Arenales", idNumber = "4-xx-xx", image = R.drawable.student2),
            Student(name = "Ivonne", lastname = "Arenales", idNumber = "4-xx-xx", image = R.drawable.student2),
            Student(name = "Ivonne", lastname = "Arenales", idNumber = "4-xx-xx", image = R.drawable.student2),
            Student(name = "Ivonne", lastname = "Arenales", idNumber = "4-xx-xx", image = R.drawable.student2),
            Student(name = "Ivonne", lastname = "Arenales", idNumber = "4-xx-xx", image = R.drawable.student2),
            Student(name = "Ivonne", lastname = "Arenales", idNumber = "4-xx-xx", image = R.drawable.student2),
        )
    }
}
