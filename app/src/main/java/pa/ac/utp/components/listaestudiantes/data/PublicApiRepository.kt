package pa.ac.utp.components.listaestudiantes.data

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pa.ac.utp.components.listaestudiantes.data.dto.PublicApi
import pa.ac.utp.components.listaestudiantes.data.services.PublicApiService
import javax.inject.Inject

class PublicApiRepository @Inject constructor(
    private val publicApiService: PublicApiService
) {
    suspend fun getRandomPublicApi(): PublicApi? = withContext(Dispatchers.IO) {
        return@withContext try {
            val response = publicApiService.getRandomPublicApi()
            if (response.isSuccessful) {
                response.body()?.entries?.first()
            } else {
                Log.e("Public", "Error code ${response.code()}")
                null
            }
        } catch (e: Exception) {
            Log.e("Public", "Error", e)
            null
        }
    }
}
