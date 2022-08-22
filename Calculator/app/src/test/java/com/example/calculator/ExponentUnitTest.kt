package com.example.calculator

import org.junit.Assert
import org.junit.Test

private val _mathOmnissiah = MathOmnissiah()

class ExponentUnitTest {

    // To the power of
    @Test
    fun `Test raising to the power of two of the same value`() {
        _mathOmnissiah.addDigit("2")
        _mathOmnissiah.callOperation(Operation.XTOY)
        _mathOmnissiah.addDigit("2")
        Assert.assertEquals("4", _mathOmnissiah.equals())
    }

    @Test
    fun `Test raising to the power of two of a different value`() {
        _mathOmnissiah.addDigit("2")
        _mathOmnissiah.callOperation(Operation.XTOY)
        _mathOmnissiah.addDigit("1")
        Assert.assertEquals("2", _mathOmnissiah.equals())
    }

    @Test
    fun `Test raising to the power of a negative`() {
        _mathOmnissiah.addDigit("2")
        _mathOmnissiah.callOperation(Operation.XTOY)
        _mathOmnissiah.addDigit("2")
        _mathOmnissiah.positiveNegative(_mathOmnissiah.numericString)
        Assert.assertEquals("0.2500000000", _mathOmnissiah.equals())
    }

    @Test
    fun `Test a negative raising to the power of a negative`() {
        _mathOmnissiah.addDigit("2")
        _mathOmnissiah.positiveNegative(_mathOmnissiah.numericString)
        _mathOmnissiah.callOperation(Operation.XTOY)
        _mathOmnissiah.addDigit("2")
        _mathOmnissiah.positiveNegative(_mathOmnissiah.numericString)
        Assert.assertEquals("4", _mathOmnissiah.equals())
    }

    @Test
    fun `Test a decimal raising to the power of a decimal`() {
        _mathOmnissiah.addDigit("2")
        _mathOmnissiah.addDecimal(_mathOmnissiah.numericString)
        _mathOmnissiah.addDigit("2")
        _mathOmnissiah.positiveNegative(_mathOmnissiah.numericString)
        _mathOmnissiah.callOperation(Operation.XTOY)
        _mathOmnissiah.addDigit("2")
        _mathOmnissiah.addDecimal(_mathOmnissiah.numericString)
        _mathOmnissiah.addDigit("2")
        _mathOmnissiah.positiveNegative(_mathOmnissiah.numericString)
        Assert.assertEquals("5.66669577875007", _mathOmnissiah.equals())
    }

}
