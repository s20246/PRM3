package pjwstk.edu.pl.s20246.prm3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

  //  private lateinit var auth: FirebaseAuth

    public val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

     //   auth = FirebaseAuth.getInstance()

        findViewById<Button>(R.id.goToRegister).setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.goToLogin).setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        val apiInterface = ApiInterface.create().getArticles()

        //apiInterface.enqueue( Callback<List<Movie>>())
        apiInterface.enqueue( object : Callback<List<Article>> {
            override fun onResponse(call: Call<List<Article>>?, response: Response<List<Article>>?) {

                if(response?.body() != null)
                    println("-------------------------------------"+(response.body()!!))
            }

            override fun onFailure(call: Call<List<Article>>?, t: Throwable?) {

            }
        })

    }


}