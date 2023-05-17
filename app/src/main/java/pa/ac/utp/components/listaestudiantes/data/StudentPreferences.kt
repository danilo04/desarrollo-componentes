package pa.ac.utp.components.listaestudiantes.data

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StudentPreferences @Inject constructor(
    @ApplicationContext appContext: Context
) {
    private val sharedPref = appContext.getSharedPreferences(PREFERENCES_FILENAME, Context.MODE_PRIVATE)

    var userToken: String?
        get() = sharedPref.getString(KEY_TOKEN, null)
        set(value) {
            val sharedPrefEdit: SharedPreferences.Editor = sharedPref.edit()
            sharedPrefEdit.putString(KEY_TOKEN, value)
            sharedPrefEdit.apply()
//            with(sharedPrefEdit) {
//                putString(KEY_TOKEN, value)
//                apply()
//            }
        }

    var eatInClass: Boolean
        get() = sharedPref.getBoolean(KEY_EAT_IN_CLASS, false)
        set(value) {
            with(sharedPref.edit()) {
                putBoolean(KEY_EAT_IN_CLASS, value)
                apply()
            }
        }

    companion object {
        private const val PREFERENCES_FILENAME = "pa.ac.utp.components.listaestudiantes.PREFERENCES_KEY_FILE"
        private const val KEY_TOKEN = "KEY_TOKEN"
        private const val KEY_EAT_IN_CLASS = "KEY_EAT_IN_CLASS"
    }
}
