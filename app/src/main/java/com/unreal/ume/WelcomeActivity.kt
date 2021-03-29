package com.unreal.ume

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_welcome.*

class WelcomeActivity : AppCompatActivity() {

    var firebaseUser : FirebaseUser? = null

    /*companion object{
        const val RC_SIGNIN=25
    }*/
    //2 variables for Firebase
    lateinit var auth: FirebaseAuth
    //lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        register_wlcm_btn.setOnClickListener {
            val intent = Intent(this@WelcomeActivity, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }
        login_wlcm_btm.setOnClickListener {
            val intent = Intent(this@WelcomeActivity, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        /*val btn = findViewById<Button>(R.id.btn)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail().requestIdToken(R.string.client_id.toString())
            .build()

        GoogleSignIn.getClient(this,gso)
        auth = Firebase.auth

        btn.setOnClickListener {
            sign_in()
        }
    }

    fun sign_in() {
        val signIntent = googleSignInClient.signInIntent
        startActivityForResult(signIntent, googleActivity.RC_SIGNIN)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode.equals(googleActivity.RC_SIGNIN)){
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            //try catch block
            try {
                val account = task.getResult(ApiException::class.java)
                doAuth(account!!.idToken)
            }catch (e: ApiException){
                Toast.makeText(this@WelcomeActivity, "Authentication failed!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun doAuth(idToken: String?){
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener{task ->
                if (task.isSuccessful){
                    startActivity(Intent(this@WelcomeActivity,MainActivity::class.java))
                    finish()
                }
                else{
                    Toast.makeText(this@WelcomeActivity, "Authentication failed!", Toast.LENGTH_SHORT).show()
                }
            }
    }



    override fun onStart() {
        super.onStart()
        firebaseUser = FirebaseAuth.getInstance().currentUser

        if(firebaseUser!=null){
            val intent = Intent(this@WelcomeActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }*/
    }
}