package pa.ac.utp.components.listaestudiantes.data.model

import androidx.annotation.DrawableRes

data class Student(
    val name: String,
    val lastname: String,
    val idNumber: String,
    @DrawableRes val image: Int,
) {
    val fullname: String
        get() = "$name $lastname"
}
