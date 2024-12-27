import android.annotation.SuppressLint
import android.content.Context
import android.location.Geocoder
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.Task
import android.location.Location
import android.util.Log
import java.util.Locale

class LocationHelper(context: Context) {
    private val fusedLocationProviderClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)

    @SuppressLint("MissingPermission") // Ensure permissions are checked before calling this
    fun getCurrentLocation(onLocationResult: (Location?) -> Unit) {
        val locationTask: Task<Location> = fusedLocationProviderClient.lastLocation
        locationTask.addOnSuccessListener { location ->
            onLocationResult(location)
        }.addOnFailureListener {
            onLocationResult(null) // Handle error or no location available
        }
    }

    fun getCityName(context: Context, latitude: Double, longitude: Double): String? {
        return try {
            val geocoder = Geocoder(context, Locale.getDefault())
            val addresses = geocoder.getFromLocation(latitude, longitude, 1)
            if (addresses?.isNotEmpty() == true) {
                addresses[0]?.locality // This returns the city name
            } else {
                null
            }
        } catch (e: Exception) {
            Log.e("GeocoderError", "Error getting city name: ${e.localizedMessage}")
            null
        }
    }
}
