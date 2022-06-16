package pjwstk.edu.pl.s20246.prm3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        findViewById<TextView>(R.id.redirectToRegisterButton).setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.buttonLogin).setOnClickListener {

            if(findViewById<EditText>(R.id.editEmailLogin).text.trim().toString().isNotEmpty() ||
                    findViewById<EditText>(R.id.editPaswordLogin).text.trim().toString().isNotEmpty()){

                signInUser(findViewById<EditText>(R.id.editEmailLogin).text.trim().toString(),
                            findViewById<EditText>(R.id.editPaswordLogin).text.trim().toString())

            } else {
                Toast.makeText(this, "Input required", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun signInUser (email:String, password:String){
        MainActivity().auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this){task ->
                if(task.isSuccessful){
                    val intent = Intent(this, DashboardActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Error"+task.exception, Toast.LENGTH_LONG).show()
                }
            }
    }
}