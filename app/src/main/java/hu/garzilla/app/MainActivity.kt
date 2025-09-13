package hu.garzilla.app

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Induláskor rögtön a TestMapActivity-t nyitja
        startActivity(Intent(this, TestMapActivity::class.java))
        finish()
    }
}
