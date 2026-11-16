package com.crystalkey

import android.inputmethodservice.InputMethodService
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.view.Gravity
import android.view.View
import android.view.inputmethod.InputConnection
import android.widget.Button
import android.widget.LinearLayout

class CrystalKeyboardService : InputMethodService() {

    private val rows = listOf(
        listOf("q","w","e","r","t","y","u","i","o","p"),
        listOf("a","s","d","f","g","h","j","k","l"),
        listOf("⇧","z","x","c","v","b","n","m","⌫"),
        listOf("?123",",","🌐","Espacio",".","↵")
    )

    override fun onCreateInputView(): View {
        val keyboard = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(5, 10, 5, 10)
            setBackgroundColor(Color.rgb(220, 240, 235))
        }

        rows.forEach { row ->
            val line = LinearLayout(this).apply {
                orientation = LinearLayout.HORIZONTAL
                gravity = Gravity.CENTER
            }

            row.forEach { key ->
                val button = Button(this).apply {
                    text = if (key == "Espacio") "" else key
                    textSize = 17f
                    setTextColor(Color.rgb(35, 75, 70))
                    typeface = Typeface.DEFAULT_BOLD
                    background = GradientDrawable().apply {
                        setColor(Color.rgb(245, 255, 252))
                        cornerRadius = 22f
                        setStroke(2, Color.rgb(180, 220, 210))
                    }

                    setOnClickListener {
                        handleKey(key)
                    }
                }

                val params = LinearLayout.LayoutParams(
                    0,
                    52,
                    if (key == "Espacio") 4f else 1f
                ).apply {
                    setMargins(3, 4, 3, 4)
                }

                line.addView(button, params)
            }

            keyboard.addView(
                line,
                LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
            )
        }

        return keyboard
    }

    private fun handleKey(key: String) {
        val connection: InputConnection =
            currentInputConnection ?: return

        when (key) {
            "⌫" -> connection.deleteSurroundingText(1, 0)
            "↵" -> connection.commitText("\n", 1)
            "Espacio" -> connection.commitText(" ", 1)
            "⇧", "🌐", "?123", "," , "." -> {}
            else -> connection.commitText(key, 1)
        }
    }
}
