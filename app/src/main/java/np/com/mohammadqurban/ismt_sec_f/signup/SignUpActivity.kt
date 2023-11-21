package np.com.mohammadqurban.ismt_sec_f.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import np.com.mohammadqurban.ismt_sec_f.HomeActivity
import np.com.mohammadqurban.ismt_sec_f.databinding.ActivitySignUpBinding
import np.com.mohammadqurban.ismt_sec_f.db.TestDatabase
import np.com.mohammadqurban.ismt_sec_f.db.User
import np.com.mohammadqurban.ismt_sec_f.login.LoginActivity
import java.lang.Exception
import java.util.regex.Pattern

class SignUpActivity : AppCompatActivity() {
    private lateinit var signUpBinding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        signUpBinding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(signUpBinding.root)

        signUpBinding.imgBtnBack.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }

        signUpBinding.btnSignUp.setOnClickListener {


            val fullName = signUpBinding.etFullName.text.toString().trim()
            val email = signUpBinding.etEmail.text.toString().trim()
            val password = signUpBinding.etPassword.text.toString().trim()
            val confirmPassword = signUpBinding.etConfirmPassword.text.toString().trim()
            fun isStrongPassword(password: String): Boolean {
                val passwordPattern = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@\$!%*?&])[A-Za-z\\d@\$!%*?&]{8,}$"
                val pattern = Pattern.compile(passwordPattern)
                val matcher = pattern.matcher(password)
                return matcher.matches()
            }


            if (signUpBinding.etEmail.text.isBlank() ||
                signUpBinding.etPassword.text.isBlank() ||
                signUpBinding.etFullName.text.isBlank() || signUpBinding.etConfirmPassword.text.isBlank()
            )
                Toast.makeText(
                    this, "One of the informations is missing",
                    Toast.LENGTH_SHORT
                ).show()
            else
                if (password != confirmPassword) {
                    Toast.makeText(
                        this, "Entered passwords are not matching",
                        Toast.LENGTH_SHORT
                    ).show()
                } else
                    if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                        Toast.makeText(
                            this, "Enter a valid email",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else
                        if (!(isStrongPassword(password))) {
                            Toast.makeText(
                                this,
                                "Password criteria did not meet",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {


                            //After Validation
                            val testDatabase = TestDatabase.getInstance(applicationContext)
                            val userDao = testDatabase.getUserDao()

                            Thread {
                                try {
                                    val userInDb = userDao.checkUserExist(email)
                                    if (userInDb == null) {
                                        //Insert into database
                                        val user = User(fullName, email, password)
                                        userDao.insertNewUser(user)
                                        runOnUiThread {
                                            clearInputFields()
                                            showToast("New user added..")
                                        }
                                    } else {
                                        runOnUiThread {
                                            showToast("User already exist with this email...")
                                        }
                                    }
                                } catch (exception: Exception) {
                                    exception.printStackTrace()
                                    runOnUiThread {
                                        showToast("Couldn't insert user...")
                                    }
                                }
                            }.start()
                        }
        }
    }


    private fun clearInputFields() {
        signUpBinding.etEmail.text.clear()
        signUpBinding.etFullName.text.clear()
        signUpBinding.etPassword.text.clear()
        signUpBinding.etConfirmPassword.text.clear()
    }

    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }
}