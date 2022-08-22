package com.example.calculator

import org.junit.Assert
import org.junit.Test

private val _mathOmnissiah = MathOmnissiah()

class MultiplyUnitTest {

    // multiplying
    @Test
    fun `Test Multiplying two of the same value`() {
        _mathOmnissiah.addDigit("2")
        _mathOmnissiah.callOperation(Operation.MULTIPLY)
        _mathOmnissiah.addDigit("2")
        Assert.assertEquals("4", _mathOmnissiah.equals())
    }

    @Test
    fun `Test Multiplying two different values`() {
        _mathOmnissiah.addDigit("3")
        _mathOmnissiah.callOperation(Operation.MULTIPLY)
        _mathOmnissiah.addDigit("2")
        Assert.assertEquals("6", _mathOmnissiah.equals())
    }

    @Test
    fun `Test Multiplying two negative values`() {
        _mathOmnissiah.addDigit("2")
        _mathOmnissiah.positiveNegative(_mathOmnissiah.numericString)
        _mathOmnissiah.callOperation(Operation.MULTIPLY)
        _mathOmnissiah.addDigit("2")
        _mathOmnissiah.positiveNegative(_mathOmnissiah.numericString)
        Assert.assertEquals("4", _mathOmnissiah.equals())
    }

    @Test
    fun `Test Multiplying a single negative value`() {
        _mathOmnissiah.addDigit("2")
        _mathOmnissiah.positiveNegative(_mathOmnissiah.numericString)
        _mathOmnissiah.callOperation(Operation.MULTIPLY)
        _mathOmnissiah.addDigit("2")
        Assert.assertEquals("-4", _mathOmnissiah.equals())
    }

    @Test
    fun `Test Multiplying a large value`() {
        for (i in 0..16) {
            _mathOmnissiah.addDigit("2")
        }
        _mathOmnissiah.callOperation(Operation.MULTIPLY)
        _mathOmnissiah.addDigit("2")
        Assert.assertEquals("4444444444444444", _mathOmnissiah.equals())
    }

    @Test
    fun `Test Multiplying a large value and having an scentific notation output`() {
        for (i in 0..16) {
            _mathOmnissiah.addDigit("2")
        }
        _mathOmnissiah.callOperation(Operation.MULTIPLY)
        _mathOmnissiah.addDigit("2")
        var result = ""
        for (y in 0..2) {
            result = _mathOmnissiah.equals()
        }
        Assert.assertEquals("1.8E16", result)
    }

    @Test
    fun `Test Multiplying a large decimal value`() {
        _mathOmnissiah.addDigit("2")
        _mathOmnissiah.addDecimal(_mathOmnissiah.numericString)
        for (i in 0..13) {
            _mathOmnissiah.addDigit("2")
        }
        _mathOmnissiah.callOperation(Operation.MULTIPLY)
        _mathOmnissiah.addDigit("2")
        Assert.assertEquals("4.44444444444444", _mathOmnissiah.equals())
    }

    @Test
    fun `Test Multiplying a large decimal value multiple times`() {
        _mathOmnissiah.addDigit("2")
        _mathOmnissiah.addDecimal(_mathOmnissiah.numericString)
        for (i in 0..13) {
            _mathOmnissiah.addDigit("2")
        }
        _mathOmnissiah.callOperation(Operation.MULTIPLY)
        _mathOmnissiah.addDigit("2")
        var result = ""
        for (y in 0..2) {
            result = _mathOmnissiah.equals()
        }
        Assert.assertEquals("1.77777777777777", result)
    }
}

