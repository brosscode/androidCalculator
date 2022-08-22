package com.example.calculator

import org.junit.Test

private val _mathOmnissiah = MathOmnissiah()

class SquaredUnitTest {

    // To the squared of
    @Test
    fun `Test to the squared of two of the same value`() {
        _mathOmnissiah.squared("2")
    }

    @Test
    fun `Test to the squared of pi`() {
        _mathOmnissiah.squared("3.14159")
    }

    @Test
    fun `Test to the squared of decimals`() {
        _mathOmnissiah.squared("2.1558")
    }

    @Test
    fun `Test to the squared of a long full number`() {3
        _mathOmnissiah.squared("123456789876543")
    }

    @Test
    fun `Test to the squared of a long decimal number`() {
        _mathOmnissiah.squared("123456789.1234567")
    }

    @Test
    fun `Test to the squared of negative values`() {
        _mathOmnissiah.squared("-2")
    }

    @Test
    fun `Test squared a longer than 16 digit full number`() {
        _mathOmnissiah.squared("12345678987654321")
    }

}
