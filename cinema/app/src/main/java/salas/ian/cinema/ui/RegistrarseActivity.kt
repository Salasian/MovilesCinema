package salas.ian.cinema.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import salas.ian.cinema.MainActivity
import salas.ian.cinema.R

class RegistrarseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrarse)

        val btn_registrarse2 = findViewById<Button>(R.id.btn_registrarse2)
        btn_registrarse2.setOnClickListener {
            val Intent = Intent(this, InicioSesionActivity::class.java)
            startActivity(Intent)
        }
    }
}