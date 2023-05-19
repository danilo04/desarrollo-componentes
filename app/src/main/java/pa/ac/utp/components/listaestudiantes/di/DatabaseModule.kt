package pa.ac.utp.components.listaestudiantes.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import pa.ac.utp.components.listaestudiantes.data.StudentsDatabase
import pa.ac.utp.components.listaestudiantes.data.dao.StudentDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun providesStudentsDatabase(@ApplicationContext context: Context): StudentsDatabase {
        return StudentsDatabase.buildDatabase(context)
    }

    @Provides
    fun providesStudentDao(studentsDatabase: StudentsDatabase): StudentDao {
        return studentsDatabase.studentDao()
    }
}
