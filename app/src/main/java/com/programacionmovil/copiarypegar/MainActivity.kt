package com.programacionmovil.copiarypegar

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    lateinit var cajaTexto : EditText
    lateinit var txtviewtextoacopiar : TextView
    lateinit var btnestablecer : Button
    lateinit var botonpegar : Button
    lateinit var txtviewpegar : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        cajaTexto=findViewById(R.id.cajatexto)
        txtviewtextoacopiar=findViewById(R.id.txtviewtextoacopiar)
        btnestablecer=findViewById(R.id.botonestablecer)
        botonpegar=findViewById(R.id.botonpegar)
        txtviewpegar=findViewById(R.id.textviewpegar)
        btnestablecer.setOnClickListener{
            establecer()
        }
        txtviewtextoacopiar.setOnClickListener{
            val clipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clipData : ClipData = ClipData.newPlainText("text/plain", txtviewtextoacopiar.text.toString())
            clipboardManager.setPrimaryClip(clipData)
            Toast.makeText(applicationContext, "Texto Copiado", Toast.LENGTH_SHORT).show()
        }
        botonpegar.setOnClickListener{
            val clipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            if (!clipboardManager.hasPrimaryClip()){
                Toast.makeText(applicationContext, "PortapapelesVacio",Toast.LENGTH_SHORT)
                return@setOnClickListener
            }
            val primaryClip = clipboardManager.primaryClip!!.getItemAt(0)
            txtviewpegar.text = primaryClip.text.toString()

        }
    }

    fun establecer(){
        if (cajaTexto.text.isEmpty()){
            Toast.makeText(applicationContext, "La caja de texto no debe estar vacia", Toast.LENGTH_SHORT).show()
            return
        }
        txtviewtextoacopiar.text=cajaTexto.text.toString()
        txtviewtextoacopiar.visibility= View.VISIBLE
        Toast.makeText(applicationContext, "Toca para copiar el texto", Toast.LENGTH_SHORT).show()
    }

}