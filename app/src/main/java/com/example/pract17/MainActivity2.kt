package com.example.pract17

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AlertDialog
import android.widget.Toast
import com.example.pract17.R.*


class MainActivity2 : AppCompatActivity() {
    private lateinit var login:EditText
    private lateinit var pass:EditText
    private lateinit var info:TextView
    private lateinit var pref: SharedPreferences

    private lateinit var builder:AlertDialog

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        login = findViewById(R.id.login)
        pass = findViewById(R.id.pass)
        info=findViewById(R.id.textView)

    }

    fun handler(v: View) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Важный выбор")
            .setMessage("Выбери что делаем")
            .setCancelable(true)
            .setPositiveButton("Сохранить") { _, _ ->
                info.text="Login: ${login.text}\n Password ${pass.text}"
                Toast.makeText(
                    this, "Сохранено",
                    Toast.LENGTH_LONG
                ).show()

                pref = getPreferences(MODE_PRIVATE)
                val ed = pref.edit()
                ed.putString("login", login.text.toString())
                ed.putString("password", pass.text.toString())
                ed.apply()
            }

            .setNegativeButton(
            "Загрузить"
        ) { _, _ ->
                val savedText=info.text.toString()
                val loginIndex=savedText.indexOf("Login:")
                val passwordIndex=savedText.indexOf("Password:")

                if(loginIndex!= -1 && passwordIndex!= -1){
                    val lodinStr=savedText.substring(loginIndex +7,passwordIndex)
                    val passStr=savedText.substring(passwordIndex +10)
                    login.setText(lodinStr)
                    pass.setText(passStr)
                }
            Toast.makeText(
                this, "загружено",
                Toast.LENGTH_LONG
            ).show()
            pref = getPreferences(MODE_PRIVATE)
            login.setText(pref.getString("login", ""))

            pass.setText(pref.getString("password", ""))
        }
        builder.create()
        builder.show()


    }


}
