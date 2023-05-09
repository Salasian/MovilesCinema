package salas.ian.cinema.ui

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import salas.ian.cinema.MainActivity
import salas.ian.cinema.R
import salas.ian.cinema.databinding.ActivityInicioSesionBinding

class InicioSesionActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityInicioSesionBinding
    private lateinit var googleSignInClient: GoogleSignInClient
    private val RC_SIGN_IN = 123
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()


        binding= ActivityInicioSesionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth= Firebase.auth

        // Configurar opciones de inicio de sesión con Google
        val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        // Crear cliente de inicio de sesión con Google
        googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions)

        val btn_ingresar = findViewById<Button>(R.id.btn_ingresar)
        val btnGoogleSignIn= findViewById<SignInButton>(R.id.sign_in_button)
        val btn_registrarse = findViewById<Button>(R.id.btn_registrarse)
        btn_ingresar.setOnClickListener {
            val Email= binding.editTextTextPersonName.text.toString()
            val Pass= binding.editTextTextPersonName2.text.toString()

            when{
                Email.isEmpty() || Pass.isEmpty()->{
                    Toast.makeText(baseContext, "Correo o contraseña incorrectos",
                        Toast.LENGTH_LONG).show()
                }else->{
                SignIn(Email, Pass)
            }
            }
        }
        btnGoogleSignIn.setOnClickListener {
            // Iniciar sesión con Google
            val signInIntent = googleSignInClient.signInIntent
            startActivityForResult(signInIntent, RC_SIGN_IN)
        }
        btn_registrarse.setOnClickListener {
            val Intent = Intent(this, RegistrarseActivity::class.java)
            startActivity(Intent)
        }
    }
////////////////////
    private fun SignIn(email:String, password:String){
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "Bienvenido!!!")
                    reload()
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Correo o contraseñas incorrectos!!.",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Autenticar al usuario con Firebase
                val account = task.getResult(ApiException::class.java)!!
                val credential = GoogleAuthProvider.getCredential(account.idToken, null)
                auth.signInWithCredential(credential)
                    .addOnCompleteListener(this) { authResult ->
                        if (authResult.isSuccessful) {
                            Log.d(TAG, "Bienvenido!!!")
                        } else {
                            Log.w(TAG, "signInWithEmail:failure", task.exception)
                            Toast.makeText(baseContext, "Correo o contraseñas incorrectos!!.",
                                Toast.LENGTH_SHORT).show()
                        }
                    }
            } catch (e: ApiException) {
                // El inicio de sesión ha fallado
            }
        }
    }

    private fun reload(){
        val intent= Intent(this, MainActivity::class.java)
        this.startActivity(intent)
        finish()
    }
}