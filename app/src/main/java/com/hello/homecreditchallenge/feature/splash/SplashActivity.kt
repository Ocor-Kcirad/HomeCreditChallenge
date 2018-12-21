package com.hello.homecreditchallenge.feature.splash

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hello.homecreditchallenge.R
import com.hello.homecreditchallenge.feature.weatherlist.WeatherListActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jetbrains.anko.intentFor

class SplashActivity : AppCompatActivity() {

    companion object {
        private const val SPLASH_DELAY = 2_000L
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        GlobalScope.launch {
            delay(SPLASH_DELAY)
            withContext(Dispatchers.Main) {
                val intent = intentFor<WeatherListActivity>()
                startActivity(intent)
                finish()
            }
        }
    }

}
