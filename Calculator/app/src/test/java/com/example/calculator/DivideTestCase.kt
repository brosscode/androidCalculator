package com.example.calculator

import org.junit.Assert
import org.junit.Test

private val _mathOmnissiah = MathOmnissiah()

class DivideUnitTest {

    // dividing
    @Test
    fun `Test Dividing two of the same value`() {
        _mathOmnissiah.addDigit("2")
        _mathOmnissiah.callOperation(Operation.DIVIDE)
        _mathOmnissiah.addDigit("2")
        Assert.assertEquals("1", _mathOmnissiah.equals())
    }

    @Test
    fun `Test Dividing two of different value`() {
        _mathOmnissiah.addDigit("5")
        _mathOmnissiah.callOperation(Operation.DIVIDE)
        _mathOmnissiah.addDigit("2")
        Assert.assertEquals("2.5", _mathOmnissiah.equals())
    }

    @Test
    fun `Test Dividing two of different value with a negative`() {
        _mathOmnissiah.addDigit("-5")
        _mathOmnissiah.callOperation(Operation.DIVIDE)
        _mathOmnissiah.addDigit("2")
        Assert.assertEquals("-2.5", _mathOmnissiah.equals())
    }

    @Test
    fun `Test Dividing two decimals `() {
        _mathOmnissiah.addDigit("4")
        _mathOmnissiah.addDecimal(_mathOmnissiah.numericString)
        _mathOmnissiah.addDigit("2")
        _mathOmnissiah.callOperation(Operation.DIVIDE)
        _mathOmnissiah.addDigit("4")
        _mathOmnissiah.addDecimal(_mathOmnissiah.numericString)
        _mathOmnissiah.addDigit("2")
        Assert.assertEquals("1", _mathOmnissiah.equals())
    }

    @Test
    fun `Test Dividing two decimals with one being negative`() {
        _mathOmnissiah.addDigit("4")
        _mathOmnissiah.addDecimal(_mathOmnissiah.numericString)
        _mathOmnissiah.positiveNegative(_mathOmnissiah.numericString)
        _mathOmnissiah.addDigit("2")
        _mathOmnissiah.callOperation(Operation.DIVIDE)
        _mathOmnissiah.addDigit("4")
        _mathOmnissiah.addDecimal(_mathOmnissiah.numericString)
        _mathOmnissiah.addDigit("2")
        Assert.assertEquals("-1", _mathOmnissiah.equals())
    }

    @Test
    fun `Test Dividing two negatives`() {
        _mathOmnissiah.addDigit("4")
        _mathOmnissiah.addDecimal(_mathOmnissiah.numericString)
        _mathOmnissiah.positiveNegative(_mathOmnissiah.numericString)
        _mathOmnissiah.addDigit("2")
        _mathOmnissiah.callOperation(Operation.DIVIDE)
        _mathOmnissiah.addDigit("4")
        _mathOmnissiah.addDecimal(_mathOmnissiah.numericString)
        _mathOmnissiah.positiveNegative(_mathOmnissiah.numericString)
        _mathOmnissiah.addDigit("2")
        Assert.assertEquals("1", _mathOmnissiah.equals())
    }
}

