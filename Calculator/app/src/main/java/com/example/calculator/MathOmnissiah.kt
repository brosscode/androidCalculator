package com.example.calculator

import android.util.Log
import com.example.calculator.BigFunctions.exp
import com.example.calculator.BigFunctions.ln
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.NumberFormat
import kotlin.math.PI


class MathOmnissiah {

    // user facing visual string that they are currently building
    var numericString: String = "0"
    var calculationHistory: String = ""
    var result: String = ""

    // user facing calculator history that shows after the equals operation is hit
    private var historyValueOne: String? = null
    private var historyValueTwo: String? = null

    // operation selected by user
    private var operation: Operation? = null

    // backend variables to do math and update overtime
    private var valueOne: String? = null
    private var valueTwo: String? = null

    // track which value we are adjusting
    private var adjustValueOne: Boolean = true
    private var equalsHitLast: Boolean = false
    private var piInUse: Boolean = false

    // takes in value from button press and adds to strings
    fun addDigit(s: String): String {
        equalsHitLast = false
        if (equalsHitLast) {clearEverything()}
        if (numericString == "0") numericString = ""
        if (numericString.length == 16) return numericString
        numericString += s
        return numericString
    }

    // adds a decimal
    fun addDecimal(s: String?): String {
        equalsHitLast = false
        if (s == null) throw IllegalStateException("s is Null!")
        numericString = if (!s.contains(".")) "$s." else s
        return numericString
    }

    // adds + or - sign
    fun positiveNegative(s: String?): String {
        equalsHitLast = false
        if (s == null) throw IllegalStateException("s is Null!")
        numericString = if (s.startsWith("-")) s.drop(1) else "-$s"
        return numericString
    }

    // removes last value in the string. will also clear history if string empty.
    fun removeLast(s: String?): String {
        equalsHitLast = false
        if (s == "0") {calculationHistory = ""; return calculationHistory}
        if (s?.isNotEmpty() == true) numericString = s.dropLast(1)
        if (numericString == "") numericString = "0"
        return numericString
    }

    // Clears variables, operand, and numericString.
    fun clearEverything(): String {
        numericString = "0"
        historyValueOne = ""
        valueOne = ""
        valueTwo = ""
        operation = null
        adjustValueOne = true
        equalsHitLast = false
        return numericString
    }

    fun clearHistory(): String {
        return ""
    }

    // sets the operation
    fun callOperation(o: Operation?) {
        operation = o
        // error catching for sqrt
        if (numericString.contains("-") && operation == Operation.SQRT ) {throw IllegalStateException("Cannot sqrt A Negative Value!")}
        // do not set values since the equals function will take care of that for these two cases
        if (operation == Operation.SQRT || operation == Operation.SQUARED) return
        if (equalsHitLast) {handleResult()}
        updateHistory(numericString)
        updateValues(numericString)
        adjustValueOne = !adjustValueOne
        numericString = "0"
    }

    // updates the history variables. called by CallOperation.
    private fun updateHistory(s: String?) {
        if (piInUse) return
        if (equalsHitLast) {historyValueOne = numericString; return}
        if (adjustValueOne) historyValueOne = s
        else historyValueTwo = s
    }

    // updates when an operation of any kind is pressed
    private fun updateValues(s: String?) {
        if (piInUse) {piInUse = false; return}
        if (equalsHitLast) {valueOne = numericString; return}
        if (adjustValueOne) valueOne = s
        else valueTwo = s
    }

    // routes to the final logic functions based on Operation. Called by buttonPressed
    fun equals(): String {
        if (numericString == "0" && operation == Operation.DIVIDE) throw IllegalStateException("Cannot Divide by Zero!")
        updateHistory(numericString)
        updateValues(numericString)
        return when (operation) {
            Operation.ADD -> { add(valueOne, valueTwo) }
            Operation.SUBTRACT -> { subtract(valueOne, valueTwo) }
            Operation.MULTIPLY -> { multiply(valueOne, valueTwo) }
            Operation.DIVIDE -> { divide(valueOne, valueTwo) }
            Operation.XTOY -> { power(valueOne, valueTwo) }
            Operation.SQRT -> { sqrt(valueOne) }
            Operation.SQUARED -> { squared(valueOne) }
            else -> {"Error"}
        }
    }

    // if we are building on the result this is called to manage how values are shuffled around
    // this should ONLY be called when "hitEqualsLast" is set to true.
    private fun handleResult() {
        result = numericString
        updateValues(result)
        equalsHitLast = false
        adjustValueOne = true
    }

    // creates history string
    fun createHistory(): String {
        var s1: String = historyValueOne.toString()
        var s2: String = historyValueTwo.toString()
        val o: Operation? = operation
        Log.d("Pi", PI.toString())
        Log.d("s1", s1)
        Log.d("s2", s2)
        if (valueOne?.toBigDecimal() == myPi.toBigDecimal()) {
            s1 = "π"
        }
        if (valueTwo?.toBigDecimal() == myPi.toBigDecimal()) {
            s2 = "π"
        }
        if (s1.length > 16) s1 = getFormattedHistoryString(s1)
        if (s2.length > 16) s2 = getFormattedHistoryString(s2)
        val operator: String? = when (o) {
            Operation.ADD -> "+"
            Operation.SUBTRACT -> "-"
            Operation.MULTIPLY -> "×"
            Operation.DIVIDE -> "÷"
            Operation.XTOY -> "^"
            Operation.SQRT -> "√"
            Operation.SQUARED -> "²"
            null -> null
        }
        if (!adjustValueOne) calculationHistory = when (operator) {
            "√" -> {
                "$operator $s2 ="
            }
            "²" -> {
                "$s2 $operator ="
            }
            else -> {
                "$s1 $operator $s2 ="
            }
        }
        else if (adjustValueOne) calculationHistory = when (operator) {
            "√" -> {
                "$operator$s1 ="
            }
            "²" -> {
                "$s1 $operator ="
            }
            else -> {
                "$s2 $operator $s1 ="
            }
        }
        return calculationHistory
    }

    // adds variables. called by equalsMath
    private fun add(x: String?, y: String?): String {
        if (x == null) throw IllegalStateException("Value One is Null!")
        if (y == null) throw IllegalStateException("Value Two is Null!")
        equalsHitLast = true
        numericString = BigDecimal(x).plus(BigDecimal(y)).toString()
        return getFormattedNumericString()
    }

    // subtract variables. called by equalsMath
    private fun subtract(x: String?, y: String?): String {
        if (x == null) throw IllegalStateException("Value One is Null!")
        if (y == null) throw IllegalStateException("Value Two is Null!")
        equalsHitLast = true
        numericString = BigDecimal(x).minus(BigDecimal(y)).toString()
        return getFormattedNumericString()
    }

    // multiplies variables. called by equalsMath
    private fun multiply(x: String?, y: String?): String {
        if (x == null) throw IllegalStateException("Value One is Null!")
        if (y == null) throw IllegalStateException("Value Two is Null!")
        equalsHitLast = true
        numericString = BigDecimal(x).times(BigDecimal(y)).toString()
        return getFormattedNumericString()
    }

    // divides variables. called by equalsMath
    private fun divide(x: String?, y: String?): String {
        if (x == null) throw IllegalStateException("Value One is Null!")
        if (y == null) throw IllegalStateException("Value Two is Null!")
        equalsHitLast = true
        numericString = BigDecimal(x).divide(BigDecimal(y)).toString()
        return getFormattedNumericString()
    }

    // exponential multiplication. called by equalsMath
    private fun power(x: String?, y: String?): String {
        if (x == null) throw IllegalStateException("Value One is Null!")
        if (y == null) throw IllegalStateException("Value Two is Null!")
        equalsHitLast = true
        val SCALE = 10
        val valOne = BigDecimal(x)
        val valTwo = BigDecimal(y)
        numericString = exp(ln(valOne, SCALE).multiply(valTwo), SCALE).toString()
        return getFormattedNumericString()
    }

    // square root. called by buttonPressed
    private fun sqrt(x: String?): String {
        if (x == null) throw IllegalStateException("x is Null!")
        equalsHitLast = true
        numericString = bigSqrt(BigDecimal(x)).toString()
        return getFormattedNumericString()
    }

    // square value. called by buttonPressed
    fun squared(x: String?): String {
        if (x == null) throw IllegalStateException("Value One is Null!")
        equalsHitLast = true
        numericString = BigDecimal(x).pow(2).toString()
        return getFormattedNumericString()
    }

    // sets value to constant pi. updates numericString to show as 3.14. called by buttonPressed. will need to interact with boolean that tracks which value we are on.
    fun pi(): String {
        numericString = "3.14"
        piInUse = true
        if (adjustValueOne) valueOne = myPi.toString()
        else valueTwo = myPi.toString()
        return numericString
    }

    fun getFormattedNumericString(): String =
        if (numericString.length > 16) {
         realFormat(BigDecimal(numericString))
        } else numericString

    private fun getFormattedHistoryString(s: String): String =
        if (s.length > 16) {
            realFormat(BigDecimal(s))
        } else s

    companion object {
        const val myPi = 3.14159
    }
}



enum class Operation {
    ADD,
    SUBTRACT,
    MULTIPLY,
    DIVIDE,
    XTOY,
    SQRT,
    SQUARED,
}




private fun realFormat(x: BigDecimal): String {
    var workingString: String?
/*
val numberOfDigits = x.toString().run {
this.length -
(if (this.contains("-")) 1 else 0) -
(if (this.contains(".")) 1 else 0)
}
*/
    workingString = format(x).orEmpty()
    if (workingString.length > 16) workingString = workingString.removeRange(16,workingString.length)
    return workingString
}

private fun format(x: BigDecimal): String? {
    val formatter: NumberFormat = DecimalFormat("0.0E0")
    formatter.roundingMode = RoundingMode.HALF_UP
    formatter.minimumFractionDigits = if (x.scale() > 0) x.precision() else x.scale()
    return formatter.format(x)
}


private val SQRT_DIG = BigDecimal(150)
private val SQRT_PRE = BigDecimal(10).pow(SQRT_DIG.toInt())

/**
 * Private utility method used to compute the square root of a BigDecimal.
 *
 * @author Luciano Culacciatti
 * @url http://www.codeproject.com/Tips/257031/Implementing-SqrtRoot-in-BigDecimal
 */
private fun sqrtNewtonRaphson(c: BigDecimal, xn: BigDecimal, precision: BigDecimal): BigDecimal? {
    val fx = xn.pow(2).add(c.negate())
    val fpx = xn.multiply(BigDecimal(2))
    var xn1 = fx.divide(fpx, 2 * SQRT_DIG.toInt(), RoundingMode.HALF_DOWN)
    xn1 = xn.add(xn1.negate())
    val currentSquare = xn1.pow(2)
    var currentPrecision = currentSquare.subtract(c)
    currentPrecision = currentPrecision.abs()
    return if (currentPrecision.compareTo(precision) <= -1) {
        xn1
    } else sqrtNewtonRaphson(c, xn1, precision)
}

/**
 * Uses Newton Raphson to compute the square root of a BigDecimal.
 *
 * @author Luciano Culacciatti
 * @url http://www.codeproject.com/Tips/257031/Implementing-SqrtRoot-in-BigDecimal
 */
fun bigSqrt(c: BigDecimal): BigDecimal? {
    return sqrtNewtonRaphson(c, BigDecimal(1), BigDecimal(1).divide(SQRT_PRE))
}

