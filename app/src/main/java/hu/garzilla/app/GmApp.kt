package hu.garzilla.app

import android.app.Application
import org.osmdroid.config.Configuration

class GmApp : Application() {
    override fun onCreate() {
        super.onCreate()
        // Egyszerű és biztos user-agent:
        Configuration.getInstance().userAgentValue = packageName
    }
}
