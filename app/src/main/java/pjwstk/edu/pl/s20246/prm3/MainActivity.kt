package pjwstk.edu.pl.s20246.prm3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

  //  private lateinit var auth: FirebaseAuth

    private val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

     //   auth = FirebaseAuth.getInstance()

        findViewById<Button>(R.id.goToRegister).setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    fun createUser(email:String, password:String){
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this){ task ->
                if(task.isSuccessful){
                    Log.e("Task message", "successful")
                } else {
                    Log.e("Task message", "Failed"+task.exception)
                }
            }
    }
}