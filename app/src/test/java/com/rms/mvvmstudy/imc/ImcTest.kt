package com.rms.mvvmstudy.imc

import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class ImcTest {

    lateinit var imc: ImcContract

    private val height = "2"
    private val weight = "50"

    @Before
    fun setup() {

        imc = Imc()
    }

    @Test
    fun `When entering the values weight 50 and height 2 the result should be 12,5`() {
        val result = imc.calculate(weight, height)
        val expected = "12.5"
        result.map {
            assertThat(it.result, `is`(ImcCalcState.Success(expected).result))
        }
    }

    @Test
    fun `When entering the values weight empty and height 2 the result should be WrongWeight`() {
        val result = imc.calculate("", height)
        result.mapLeft {
            assertTrue(it.contains(ImcCalcState.EmptyWeight))
        }
    }

    @Test
    fun `When entering the values weight 50 and height empty the result should be WrongHeight`() {
        val result = imc.calculate(weight, "")
        result.mapLeft {
            assertTrue(it.contains(ImcCalcState.EmptyHeight))
        }
    }

    @Test
    fun `When entering the values weight 0 and height 2 the result should be WeightIsZero`() {
        val result = imc.calculate("0.0", height)
        result.mapLeft {
            assertTrue(it.contains(ImcCalcState.WeightIsZero))
        }
    }

    @Test
    fun `When entering the values weight 50 and height 0 the result should be HeightIsZero`() {
        val result = imc.calculate(weight, "0.0")
        result.mapLeft {
            assertTrue(it.contains(ImcCalcState.HeightIsZero))
        }
    }

    @Test
    fun `When entering the values weight empty and height empty the result should be WrongHeight`() {
        val result = imc.calculate("", "")
        result.mapLeft {
            assertTrue(it.contains(ImcCalcState.EmptyWeight))
            assertTrue(it.contains(ImcCalcState.EmptyHeight))
        }
    }
}