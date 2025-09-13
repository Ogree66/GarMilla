package hu.garzilla.app

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.osmdroid.config.Configuration
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay

class GarMillaTerkepActivity : ComponentActivity() {

    private lateinit var mapView: MapView
    private lateinit var locationOverlay: MyLocationNewOverlay

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_garmilla_terkep)

        // OSM user agent
        Configuration.getInstance().userAgentValue = packageName

        // Térkép inicializálás
        mapView = findViewById(R.id.mapView)
        mapView.setMultiTouchControls(true)
        mapView.controller.setZoom(15.0)
        mapView.controller.setCenter(GeoPoint(47.15237, 19.62158))

        // Saját pozíció overlay
        locationOverlay = MyLocationNewOverlay(GpsMyLocationProvider(this), mapView)
        locationOverlay.enableMyLocation()
        locationOverlay.enableFollowLocation()
        locationOverlay.isDrawAccuracyEnabled = true

        // Kék háromszög ikon betöltése
        val arrowDrawable = ContextCompat.getDrawable(this, R.drawable.ic_arrow_blue)
        if (arrowDrawable != null) {
            val arrowBitmap = drawableToBitmap(arrowDrawable)
            locationOverlay.setDirectionArrow(arrowBitmap, arrowBitmap)
        }

        // Overlay hozzáadása
        mapView.overlays.add(locationOverlay)

        // "Ugorj rám" gomb
        val fabMyLocation: FloatingActionButton = findViewById(R.id.fabMyLocation)
        fabMyLocation.setOnClickListener {
            val myLocation = locationOverlay.myLocation
            if (myLocation != null) {
                mapView.controller.animateTo(myLocation)
                Toast.makeText(this, "📍 Ugrottam rád!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "❌ Nincs GPS pozíció!", Toast.LENGTH_SHORT).show()
            }
        }

        // Engedélykérés
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                1
            )
        }
    }

    // Drawable → Bitmap konverzió
    private fun drawableToBitmap(drawable: Drawable): Bitmap {
        if (drawable is BitmapDrawable && drawable.bitmap != null) {
            return drawable.bitmap
        }
        val bitmap = Bitmap.createBitmap(
            drawable.intrinsicWidth,
            drawable.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)
        return bitmap
    }
}
