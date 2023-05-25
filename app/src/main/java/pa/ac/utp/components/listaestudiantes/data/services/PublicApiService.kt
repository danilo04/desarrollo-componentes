package pa.ac.utp.components.listaestudiantes.data.services

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import pa.ac.utp.components.listaestudiantes.data.dto.PublicApi
import retrofit2.Response
import retrofit2.http.GET

interface PublicApiService {
    // https://api.publicapis.org/random
    @GET("random")
    suspend fun getRandomPublicApi(): Response<PublicApiResponse>
}

@JsonClass(generateAdapter = true)
data class PublicApiResponse(
    @field:Json(name = "count") val count: Int,
    @field:Json(name = "entries") val entries: List<PublicApi>
)
