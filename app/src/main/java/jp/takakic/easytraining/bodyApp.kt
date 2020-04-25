package jp.takakic.easytraining

import android.app.Application
import io.realm.Realm

class bodyApp: Application() {
    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
    }
}