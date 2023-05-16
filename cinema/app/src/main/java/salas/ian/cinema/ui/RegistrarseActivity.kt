package salas.ian.cinema.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import salas.ian.cinema.MainActivity
import salas.ian.cinema.R

class RegistrarseActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrarse)

        auth = FirebaseAuth.getInstance()

        val contrasena1= findViewById<EditText>(R.id.editTextTextPersonName4)
        val contrasena2= findViewById<EditText>(R.id.editTextTextPersonName6)

        val btn_registrarse2 = findViewById<Button>(R.id.btn_registrarse2)
        btn_registrarse2.setOnClickListener {
            // Obtener email y contraseña ingresados por el usuario
            val email = findViewById<EditText>(R.id.editTextTextPersonName7).text.toString()
            val password1= contrasena1.text.toString().toLowerCase()
            val password2= contrasena2.text.toString().toLowerCase()

            if(password1 == password2){
            // Crear cuenta de usuario en Firebase
            auth.createUserWithEmailAndPassword(email, password1)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // La cuenta de usuario se creó exitosamente
                        val user = auth.currentUser
                        Toast.makeText(this, "Cuenta creada con éxito", Toast.LENGTH_SHORT).show()

                        // Redirigir al usuario a la pantalla de inicio de sesión
                        val intent = Intent(this, InicioSesionActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        // Se produjo un error al crear la cuenta de usuario
                        Toast.makeText(this, "Error al crear cuenta: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            else{
                Toast.makeText(this, "Las conntraseñas no coinciden", Toast.LENGTH_SHORT).show()
            }
        }
    }
}