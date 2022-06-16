package pjwstk.edu.pl.s20246.prm3

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity: AppCompatActivity() {

    //private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        //auth = FirebaseAuth.getInstance()

        findViewById<Button>(R.id.buttonRegister).setOnClickListener {

            if(findViewById<EditText>(R.id.editEmail).text.trim().toString().isNotEmpty() ||
                findViewById<EditText>(R.id.editPasword).text.trim().toString().isNotEmpty()){

                createUser(findViewById<EditText>(R.id.editEmail).text.trim().toString(),
                    findViewById<EditText>(R.id.editPasword).text.trim().toString())

            } else {

                Toast.makeText(this, "Input required", Toast.LENGTH_LONG).show()

            }
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