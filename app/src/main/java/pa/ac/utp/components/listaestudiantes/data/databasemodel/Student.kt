package pa.ac.utp.components.listaestudiantes.data.databasemodel

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "students",
    indices = [
        Index(name = "index_id_number", value = ["id_number"], unique = true)
    ]
)
data class Student(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "lastname") val lastname: String,
    @ColumnInfo(name = "id_number") val idNumber: String
) {
    val fullname: String
        get() = "$name $lastname"
}
