package salas.ian.cinema.ui.cartelera

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RatingBar
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import salas.ian.cinema.R
import java.util.Date

class agregarOpinion : AppCompatActivity() {
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_opinion)

        db = FirebaseFirestore.getInstance()
        val cancelar: Button = findViewById(R.id.cancelar)
        val calificar: Button = findViewById(R.id.calificar)

        cancelar.setOnClickListener{
            val intent= Intent(this, ratingCelda::class.java)
            startActivity(intent)
        }

        calificar.setOnClickListener {
            guardarOpinion()
        }

    }

    private fun guardarOpinion() {
        // Obtiene los valores de los campos de la interfaz de usuario

        val nombreUsuario = findViewById<EditText>(R.id.edit2).text.toString()
        val comentario = findViewById<EditText>(R.id.edit).text.toString()
        val calificacion = findViewById<RatingBar>(R.id.rating).rating

        // Crea un objeto Opinion con los datos del comentario
        val opinion = Opinion(
            nombre = nombreUsuario,
            fecha = Date().toString(),
            comentario = comentario,
            calificacion = calificacion
        )

        db.collection("comentarios")
            .add(opinion)
            .addOnSuccessListener { documentReference ->
                val comentarioId = documentReference.id

                Toast.makeText(this, "Comentario guardado correctamente", Toast.LENGTH_LONG).show()
                val intent = Intent(this, ratingCelda::class.java)
                startActivity(intent)
                finish()
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Error al guardar el comentario", Toast.LENGTH_SHORT).show()
                }
        }
}