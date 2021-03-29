package com.unreal.ume

import android.accessibilityservice.AccessibilityButtonController
import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.auth.api.credentials.IdToken
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.GooglePlayServicesUtilLight
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_google.*

class googleActivity : AppCompatActivity() {

    //val CAMERA_RQ = 102

    companion object{
        const val RC_SIGNIN=25
    }
    //2 variables for Firebase
    lateinit var auth: FirebaseAuth
    lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_google)

        //buttontap()

        val btn = findViewById<Button>(R.id.btn)

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
        startActivityForResult(signIntent, RC_SIGNIN)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode.equals(RC_SIGNIN)){
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            //try catch block
            try {
                val account = task.getResult(ApiException::class.java)
                doAuth(account!!.idToken)
            }catch (e: ApiException){
                Toast.makeText(this@googleActivity, "Authentication failed!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun doAuth(idToken: String?){
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener{task ->
                if (task.isSuccessful){
                    startActivity(Intent(this@googleActivity,MainActivity::class.java))
                    finish()
                }
                else{
                    Toast.makeText(this@googleActivity, "Authentication failed!", Toast.LENGTH_SHORT).show()
                }
            }
    }

    /**REQUESTING PERMISSIONS

    private fun buttontap(){
        permission_btn.setOnClickListener{
            checkForePermission(android.Manifest.permission.CAMERA, "Internet", CAMERA_RQ)
        }
    }

    private fun checkForePermission(Permission: String,name:String, requestCode:String){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            when{
                ContextCompat.checkSelfPermission(applicationContext, Permission) == PackageManager.PERMISSION_GRANTED -> {
                    Toast.makeText(applicationContext, "$name permission granted", Toast.LENGTH_SHORT).show()
                }
                shouldShowRequestPermissionRationale(Permission) -> showDialog(Permission, name, requestCode)

                else -> ActivityCompat.requestPermissions(this, arrayOf(Permission), requestCode)
            }
        }
        when (requestCode){
            CAMERA_RQ -> innerCheck("Camera")
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        fun innercheck(name:String){
            if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED){
                Toast.makeText(applicationContext, "$name Permission refused", Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(applicationContext, "$name Permission granted", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun showDialog(Permission: String,name:String, requestCode:String){
        val builder = AlertDialog.Builder(this)
        builder.apply {
            setMessage("Permission to access your $name is required to use this app")
            setTitle("Permission required")
            setPositiveButton("OK"){dialog, which ->
                ActivityCompat.requestPermissions(this@googleActivity, arrayOf(Permission), requestCode)
            }
        }
        val dialog = builder.create()
        dialog.show()
    }**/


}