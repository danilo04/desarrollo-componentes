package pa.ac.utp.components.listaestudiantes.data

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import pa.ac.utp.components.listaestudiantes.data.dao.StudentDao
import pa.ac.utp.components.listaestudiantes.data.model.Student

private const val DATABASE_VERSION = 1
private const val DATABASE_NAME = "StudentDatabase"

@Database(entities = [Student::class], version = DATABASE_VERSION)
abstract class StudentsDatabase : RoomDatabase() {
    abstract fun studentDao(): StudentDao

    companion object {
        fun buildDatabase(context: Context): StudentsDatabase {
            return Room.databaseBuilder(context, StudentsDatabase::class.java, DATABASE_NAME)
                .addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        val student = Student(
                            name = "Juan",
                            lastname = "Perez",
                            idNumber = "7-44-2434"
                        )

                        val contentValues = ContentValues()
                        contentValues.put("name", student.name)
                        contentValues.put("lastname", student.lastname)
                        contentValues.put("id_number", student.idNumber)
                        db.insert("students", SQLiteDatabase.CONFLICT_NONE, contentValues)
                    }
                })
                .build()
        }
    }
}
