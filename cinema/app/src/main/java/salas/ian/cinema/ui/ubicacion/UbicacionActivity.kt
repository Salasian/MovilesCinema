package salas.ian.cinema.ui.ubicacion

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import salas.ian.cinema.R
import salas.ian.cinema.ui.InicioSesionActivity

class UbicacionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ubicacion)

        val btn_ubicacion_exacta = findViewById<Button>(R.id.btn_ubicacion_exacta)
        btn_ubicacion_exacta.setOnClickListener {
            val Intent = Intent(this, UbicacionExactaActivity::class.java)
            startActivity(Intent)
        }

    }
}