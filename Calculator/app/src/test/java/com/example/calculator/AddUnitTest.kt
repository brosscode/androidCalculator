package com.example.calculator

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test
import kotlin.math.PI

private val _mathOmnissiah = MathOmnissiah()

class AddUnitTests {
// assert the numericString

    // Adding
    @Test
    fun `Adding a digit`() {
        _mathOmnissiah.addDigit("2")
        assertEquals("2", _mathOmnissiah.numericString)
    }

    @Test
    fun `Adding 17 digits but only fitting 16`() {
        for (i in 0..16) {
            _mathOmnissiah.addDigit("2")
        }
        assertEquals("2222222222222222", _mathOmnissiah.numericString)
    }

    @Test
    fun `Test adding two of the same value`() {
        _mathOmnissiah.addDigit("2")
        _mathOmnissiah.callOperation(Operation.ADD)
        _mathOmnissiah.addDigit("2")
        assertEquals("4", _mathOmnissiah.equals())
    }

    @Test
    fun `Test adding two different values `() {
        _mathOmnissiah.addDigit("4")
        _mathOmnissiah.callOperation(Operation.ADD)
        _mathOmnissiah.addDigit("2")
        assertEquals("6", _mathOmnissiah.equals())
    }

    @Test
    fun `Test adding pi`() {
        _mathOmnissiah.pi()
        _mathOmnissiah.callOperation(Operation.ADD)
        _mathOmnissiah.addDigit("2")
        assertEquals((PI + 2).toString(), _mathOmnissiah.equals())
    }

    @Test
    fun `Test adding a decimal and whole number`() {
        _mathOmnissiah.addDigit("2")
        _mathOmnissiah.addDecimal(_mathOmnissiah.numericString)
        _mathOmnissiah.addDigit("1")
        _mathOmnissiah.addDigit("5")
        _mathOmnissiah.callOperation(Operation.ADD)
        _mathOmnissiah.addDigit("2")
        assertEquals("4.15", _mathOmnissiah.equals())
    }

    @Test
    fun `Test adding decimals`() {
        _mathOmnissiah.addDigit("2")
        _mathOmnissiah.addDecimal(_mathOmnissiah.numericString)
        _mathOmnissiah.addDigit("1")
        _mathOmnissiah.addDigit("5")
        _mathOmnissiah.callOperation(Operation.ADD)
        _mathOmnissiah.addDigit("2")
        _mathOmnissiah.addDecimal(_mathOmnissiah.numericString)
        _mathOmnissiah.addDigit("5")
        _mathOmnissiah.addDigit("5")
        assertEquals("4.70", _mathOmnissiah.equals())
    }

    @Test
    fun `Test adding a long full number with a lot of zeros`() {
        _mathOmnissiah.addDigit("1")
        for (i in 0..15) {
            _mathOmnissiah.addDigit("0")
        }
        _mathOmnissiah.callOperation(Operation.ADD)
        _mathOmnissiah.addDigit("1")
        for (j in 0..15) {
            _mathOmnissiah.addDigit("0")
        }
        assertEquals("2000000000000000", _mathOmnissiah.equals())
    }

    @Test
    fun `Test adding a long full number with a lot of zeros until it is shown as 1E16`() {
        _mathOmnissiah.addDigit("1")
        for (i in 0..15) {
            _mathOmnissiah.addDigit("0")
        }
        _mathOmnissiah.callOperation(Operation.ADD)
        _mathOmnissiah.addDigit("1")
        for (j in 0..15) {
            _mathOmnissiah.addDigit("0")
        }
        var result = ""
        for (y in 0..8) {
            result = _mathOmnissiah.equals()
        }
        assertEquals("1E16", result)
    }

    @Test
    fun `Test adding a long full number with 17 digits`() {
        _mathOmnissiah.addDigit("1")
        for (i in 0..16) {
            _mathOmnissiah.addDigit("0")
        }
        _mathOmnissiah.callOperation(Operation.ADD)
        _mathOmnissiah.addDigit("1")
        for (j in 0..16) {
            _mathOmnissiah.addDigit("0")
        }
        assertNotEquals("2E16", _mathOmnissiah.equals())
    }

    @Test
    fun `Test adding a long full number`() {
        for (i in 1..9) {
            _mathOmnissiah.addDigit(i.toString())
        }
        for (i in 8 downTo 2) {
            _mathOmnissiah.addDigit(i.toString())
        }
        _mathOmnissiah.callOperation(Operation.ADD)
        for (j in 1..9) {
            _mathOmnissiah.addDigit(j.toString())
        }
        for (j in 8 downTo 2) {
            _mathOmnissiah.addDigit(j.toString())
        }
        assertEquals("2469135797530864", _mathOmnissiah.equals())
    }

    @Test
    fun `Test adding a long decimal number`() {
        for (i in 1..9) {
            _mathOmnissiah.addDigit(i.toString())
        }
        _mathOmnissiah.addDecimal(_mathOmnissiah.numericString)
        for (i in 8 downTo 3) {
            _mathOmnissiah.addDigit(i.toString())
        }
        _mathOmnissiah.callOperation(Operation.ADD)
        for (j in 1..9) {
            _mathOmnissiah.addDigit(j.toString())
        }
        _mathOmnissiah.addDecimal(_mathOmnissiah.numericString)
        for (j in 8 downTo 3) {
            _mathOmnissiah.addDigit(j.toString())
        }
        assertEquals("246913579.753086", _mathOmnissiah.equals())
    }

    @Test
    fun `Test adding negative values`() {
        _mathOmnissiah.addDigit("2")
        _mathOmnissiah.addDecimal(_mathOmnissiah.numericString)
        _mathOmnissiah.addDigit("1")
        _mathOmnissiah.addDigit("5")
        _mathOmnissiah.callOperation(Operation.ADD)
        _mathOmnissiah.addDigit("2")
        _mathOmnissiah.addDecimal(_mathOmnissiah.numericString)
        _mathOmnissiah.addDigit("5")
        _mathOmnissiah.addDigit("5")
        assertEquals("4.70", _mathOmnissiah.equals())
    }

    @Test
    fun `Test adding two negative values`() {
        _mathOmnissiah.addDigit("2")
        _mathOmnissiah.positiveNegative(_mathOmnissiah.numericString)
        _mathOmnissiah.callOperation(Operation.ADD)
        _mathOmnissiah.addDigit("2")
        _mathOmnissiah.positiveNegative(_mathOmnissiah.numericString)
        assertEquals("-4", _mathOmnissiah.equals())
    }

    @Test
    fun `Test adding two different negative decimal values`() {
        _mathOmnissiah.addDigit("2")
        _mathOmnissiah.positiveNegative(_mathOmnissiah.numericString)
        _mathOmnissiah.addDecimal(_mathOmnissiah.numericString)
        _mathOmnissiah.addDigit("1")
        _mathOmnissiah.addDigit("5")
        _mathOmnissiah.callOperation(Operation.ADD)
        _mathOmnissiah.addDigit("2")
        _mathOmnissiah.positiveNegative(_mathOmnissiah.numericString)
        _mathOmnissiah.addDecimal(_mathOmnissiah.numericString)
        _mathOmnissiah.addDigit("5")
        _mathOmnissiah.addDigit("5")
        assertEquals("-4.70", _mathOmnissiah.equals())
    }

    @Test
    fun `Test adding a long negative decimal number that equals out to zero`() {
        for (i in 1..9) {
            _mathOmnissiah.addDigit(i.toString())
        }
        _mathOmnissiah.addDecimal(_mathOmnissiah.numericString)
        for (i in 8 downTo 3) {
            _mathOmnissiah.addDigit(i.toString())
        }
        _mathOmnissiah.positiveNegative(_mathOmnissiah.numericString)
        _mathOmnissiah.callOperation(Operation.ADD)
        for (j in 1..9) {
            _mathOmnissiah.addDigit(j.toString())
        }
        _mathOmnissiah.addDecimal(_mathOmnissiah.numericString)
        for (j in 8 downTo 3) {
            _mathOmnissiah.addDigit(j.toString())
        }
        assertEquals("0", _mathOmnissiah.equals())
    }

}