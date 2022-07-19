package com.example.simpletodoapp

import com.example.simpletodoapp.util.Validator
import junit.framework.TestCase
import org.junit.Test

import org.junit.Assert.*

class ValidatorTest {
    @Test
    @Throws(Exception::class)
    fun nonNullChecker() {
        TestCase.assertEquals(Validator.NullChecker.isNonNullString("NON NULL"), true)
        TestCase.assertEquals(Validator.NullChecker.isNonNullString(null), false)
        TestCase.assertEquals(Validator.NullChecker.isNonNullString(" "), false)
    }
}