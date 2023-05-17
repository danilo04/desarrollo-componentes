package pa.ac.utp.components.listaestudiantes

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.File

@HiltAndroidApp
class StudentListApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        GlobalScope.launch {
            val studentListFile = File(filesDir, STUDENT_LIST_FILE)
            if (!studentListFile.exists()) {
                openFileOutput(STUDENT_LIST_FILE, Context.MODE_PRIVATE).use {
                    it.write("Laury,Arenales,4-xx-xx,1\n".toByteArray())
                    it.write("Rumi,Smith,8-xx-xx,1\n".toByteArray())
                    it.write("Mel,Nielsen,4-xx-xx,2\n".toByteArray())
                    it.write("Joseph,Arenales,4-xx-xx,1\n".toByteArray())
                    it.write("Ivonne,Arenales,4-xx-xx,1\n".toByteArray())
                }
            }
        }
    }

    companion object {
        const val STUDENT_LIST_FILE = "student_list.csv"
    }
}
