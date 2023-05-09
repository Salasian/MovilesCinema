package salas.ian.cinema.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import salas.ian.cinema.databinding.ActivityMainBinding
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import salas.ian.cinema.MainActivity
import salas.ian.cinema.R

class InicioSesionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio_sesion)
        installSplashScreen()

        val btn_ingresar = findViewById<Button>(R.id.btn_ingresar)
        val btn_registrarse = findViewById<Button>(R.id.btn_registrarse)
        btn_ingresar.setOnClickListener {
            val Intent = Intent(this,MainActivity::class.java)
            startActivity(Intent)
        }
        btn_registrarse.setOnClickListener {
            val Intent = Intent(this, RegistrarseActivity::class.java)
            startActivity(Intent)
        }
    }
}