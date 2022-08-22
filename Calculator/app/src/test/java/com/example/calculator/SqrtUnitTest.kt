package com.example.calculator

import org.junit.Assert
import org.junit.Test

private val _mathOmnissiah = MathOmnissiah()

class SqrtUnitTest {

    // To the sqrt of
    @Test
    fun `Test raising to the power of two of the same value`() {
        _mathOmnissiah.addDigit("2")
        _mathOmnissiah.callOperation(Operation.XTOY)
        _mathOmnissiah.addDigit("2")
        Assert.assertEquals("4", _mathOmnissiah.equals())
    }

}
