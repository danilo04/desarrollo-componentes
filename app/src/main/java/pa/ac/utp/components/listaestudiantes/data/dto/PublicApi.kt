package pa.ac.utp.components.listaestudiantes.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PublicApi(
    @field:Json(name = "API") val api: String,
    @field:Json(name = "Description") val description: String,
    @field:Json(name = "Link") val link: String,
)
