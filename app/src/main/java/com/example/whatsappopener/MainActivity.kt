package com.example.whatsappopener

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var number = ""
        if(intent.action == Intent.ACTION_PROCESS_TEXT){
            number = intent.getCharSequenceExtra(Intent.EXTRA_PROCESS_TEXT).toString()
        }
        number.replace("[^0-9]".toRegex(), "")
        if(number.length >= 10){
            startWhatsapp(number)
        }else{
            Toast.makeText(this, "Please check the number", Toast.LENGTH_SHORT).show()
        }
    }
    private fun startWhatsapp(number: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.setPackage("com.whatsapp")
        val data:String = if(number.length == 10){
            "91$number"
        }else{
            number
        }
        intent.data = Uri.parse("https://wa.me/$data")
        try {
            startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(this, "Please install Whatsapp", Toast.LENGTH_SHORT).show()
        }
        finish()
    }
}