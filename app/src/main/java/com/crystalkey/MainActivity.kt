package com.crystalkey

import android.app.Activity
import android.os.Bundle
import android.content.Intent
import android.provider.Settings
import android.view.Gravity
import android.view.inputmethod.InputMethodManager
import android.content.Context
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.graphics.Color

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            setPadding(40, 40, 40, 40)
            setBackgroundColor(Color.rgb(225, 245, 240))
        }

        val title = TextView(this).apply {
            text = "CRYSTAL KEY"
            textSize = 30f
            setTextColor(Color.rgb(40, 90, 85))
            gravity = Gravity.CENTER
        }

        val description = TextView(this).apply {
            text = "Tu teclado. Tu estilo. Tu cristal."
            textSize = 16f
            setTextColor(Color.DKGRAY)
            gravity = Gravity.CENTER
        }

        val settingsButton = Button(this).apply {
            text = "Activar Crystal Key"
            setOnClickListener {
                startActivity(Intent(Settings.ACTION_INPUT_METHOD_SETTINGS))
            }
        }

        val selectButton = Button(this).apply {
            text = "Seleccionar teclado"
            setOnClickListener {
                val imm = getSystemService(
                    Context.INPUT_METHOD_SERVICE
                ) as InputMethodManager
                imm.showInputMethodPicker()
            }
        }

        layout.addView(title)
        layout.addView(description)
        layout.addView(settingsButton)
        layout.addView(selectButton)

        setContentView(layout)
    }
}
