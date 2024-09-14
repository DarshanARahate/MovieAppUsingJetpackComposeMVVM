import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

class SecurePreferences(context: Context) {
    // Create a MasterKey
    private val masterKey = MasterKey.Builder(context)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()

    // Create EncryptedSharedPreferences
    private val sharedPreferences = EncryptedSharedPreferences.create(
        context,
        "movies_secure_prefs",
        masterKey,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    // Function to save data
    fun saveData(key: String, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    // Function to get data
    fun getData(key: String): String? {
        return sharedPreferences.getString(key, null)
    }

    // Function to save data
    fun saveData(key: String, value: Int) {
        sharedPreferences.edit().putInt(key, value).apply()
    }

    // Function to get data
    fun getDataInt(key: String): Int {
        return sharedPreferences.getInt(key, 0)
    }
}
