import android.annotation.SuppressLint
import android.content.Context
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.Task
import android.location.Location

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
}
