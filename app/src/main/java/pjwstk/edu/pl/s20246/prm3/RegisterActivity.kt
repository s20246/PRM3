package pjwstk.edu.pl.s20246.prm3

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        findViewById<Button>(R.id.buttonRegister).setOnClickListener {

            if(findViewById<EditText>(R.id.editEmailRegister).text.trim().toString().isNotEmpty() ||
                findViewById<EditText>(R.id.editPaswordRegister).text.trim().toString().isNotEmpty()){

                createUser(findViewById<EditText>(R.id.editEmailRegister).text.trim().toString(),
                    findViewById<EditText>(R.id.editPaswordRegister).text.trim().toString())

            } else {

                Toast.makeText(this, "Input required", Toast.LENGTH_LONG).show()

            }
        }

        findViewById<TextView>(R.id.redirectToLoginButton).setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
    fun createUser(email:String, password:String){
        MainActivity().auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this){ task ->
                if(task.isSuccessful){
                    Log.e("Task message", "successful")
                    val intent = Intent(this, DashboardActivity::class.java)
                    startActivity(intent)
                } else {
                    Log.e("Task message", "Failed"+task.exception)
                }
            }
    }

}