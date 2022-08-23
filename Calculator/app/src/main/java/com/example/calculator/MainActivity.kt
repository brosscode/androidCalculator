package com.example.calculator

import android.R.attr.label
import android.annotation.SuppressLint
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

// add preferences!!!
    // should load last values from last state

class MainActivity : AppCompatActivity() {

    private val _mathOmnissiah = MathOmnissiah()
    private val mathOmnissiah: MathOmnissiah
        get() = _mathOmnissiah



    @SuppressLint("CutPasteId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) {
            mathOmnissiah.calculationHistory = savedInstanceState.getString("CalcHistory").toString()
            mathOmnissiah.numericString = savedInstanceState.getString("numericString").toString()
        }
        setContentView(R.layout.activity_main)

        val prefs = getPreferences(Context.MODE_PRIVATE)

        findViewById<TextView>(R.id.userInput).text = prefs.getString(STORED_RESULT, "0")
        findViewById<TextView>(R.id.calculatorHistory).text = prefs.getString(STORED_HISTORY, "")


        if (findViewById<TextView>(R.id.userInput).text == null) {
            findViewById<TextView>(R.id.userInput).text = "0"
        }

        val displayText = findViewById<TextView>(R.id.userInput)
        val historyText = findViewById<TextView>(R.id.calculatorHistory)

        findViewById<TextView>(R.id.userInput).setOnLongClickListener {
            it as TextView
            val converted = it.text.toString().apply {
                copy(this)
            }
            Toast.makeText(this, "Copied!", Toast.LENGTH_SHORT).show()
            true
        }


        findViewById<TextView>(R.id.zero).setOnClickListener { displayText.text = _mathOmnissiah.addDigit("0") }
        findViewById<TextView>(R.id.one).setOnClickListener { displayText.text = _mathOmnissiah.addDigit("1") }
        findViewById<TextView>(R.id.two).setOnClickListener { displayText.text = _mathOmnissiah.addDigit("2") }
        findViewById<TextView>(R.id.three).setOnClickListener { displayText.text = _mathOmnissiah.addDigit("3") }
        findViewById<TextView>(R.id.four).setOnClickListener { displayText.text = _mathOmnissiah.addDigit("4") }
        findViewById<TextView>(R.id.five).setOnClickListener { displayText.text = _mathOmnissiah.addDigit("5") }
        findViewById<TextView>(R.id.six).setOnClickListener { displayText.text = _mathOmnissiah.addDigit("6") }
        findViewById<TextView>(R.id.seven).setOnClickListener { displayText.text = _mathOmnissiah.addDigit("7") }
        findViewById<TextView>(R.id.eight).setOnClickListener { displayText.text = _mathOmnissiah.addDigit("8") }
        findViewById<TextView>(R.id.nine).setOnClickListener { displayText.text = _mathOmnissiah.addDigit("9") }

        findViewById<TextView>(R.id.plus).setOnClickListener { _mathOmnissiah.callOperation(Operation.ADD) }
        findViewById<TextView>(R.id.minus).setOnClickListener { _mathOmnissiah.callOperation(Operation.SUBTRACT) }
        findViewById<TextView>(R.id.multiply).setOnClickListener { _mathOmnissiah.callOperation(Operation.MULTIPLY) }
        findViewById<TextView>(R.id.divide).setOnClickListener { _mathOmnissiah.callOperation(Operation.DIVIDE) }

        findViewById<TextView>(R.id.clearInput).setOnClickListener {
            displayText.text = _mathOmnissiah.clearEverything()
            historyText.text = _mathOmnissiah.clearHistory()
        }
        findViewById<ImageView>(R.id.backSpace).setOnClickListener {
            if (displayText.text == "0") historyText.text = _mathOmnissiah.removeLast(_mathOmnissiah.numericString)
            else displayText.text = _mathOmnissiah.removeLast(_mathOmnissiah.numericString) }
        findViewById<TextView>(R.id.decimal).setOnClickListener { displayText.text = _mathOmnissiah.addDecimal(mathOmnissiah.getFormattedNumericString()) }
        findViewById<TextView>(R.id.plusMinus).setOnClickListener { displayText.text = _mathOmnissiah.positiveNegative(mathOmnissiah.getFormattedNumericString()) }

        findViewById<TextView>(R.id.xToTheY)?.setOnClickListener { _mathOmnissiah.callOperation(Operation.XTOY) }
        findViewById<TextView>(R.id.pi)?.setOnClickListener { displayText.text = _mathOmnissiah.pi() }
        findViewById<TextView>(R.id.squared)?.setOnClickListener { _mathOmnissiah.callOperation(Operation.SQUARED); displayText.text = _mathOmnissiah.equals() }
        findViewById<TextView>(R.id.squareRoot)?.setOnClickListener {
            try {_mathOmnissiah.callOperation(Operation.SQRT); displayText.text = _mathOmnissiah.equals()}
            catch (e: Exception) {Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()}
        }

        findViewById<TextView>(R.id.equals).setOnClickListener {
            try {
                displayText.text = _mathOmnissiah.equals()
                historyText.text = _mathOmnissiah.createHistory()
                findViewById<TextView>(R.id.userInput).startAnimation(
                    AnimationUtils.loadAnimation(
                        this,
                        R.anim.fade_in
                    )

                )
                with(prefs.edit()) {
                    putString(STORED_RESULT, _mathOmnissiah.numericString)
                    putString(STORED_HISTORY, _mathOmnissiah.calculationHistory)
                    apply()
                }
            } catch (e: Exception) {
                Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        super.onSaveInstanceState(savedInstanceState)
        val numericString = findViewById<TextView>(R.id.userInput).text
        val history = findViewById<TextView>(R.id.calculatorHistory).text
        // Save UI state changes to the savedInstanceState.
        // This bundle will be passed to onCreate if the process is
        // killed and restarted.
        savedInstanceState.putString("CalcHistory", history.toString())
        savedInstanceState.putString("numericString", numericString.toString())
        // etc.
    }

    private fun copy(s: String) {
        val clipboard = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText(label.toString(), s)
        clipboard.setPrimaryClip(clip)
    }

    companion object Preferences {
        const val STORED_RESULT = "stored_result"
        const val STORED_HISTORY = "stored_history"
    }
}