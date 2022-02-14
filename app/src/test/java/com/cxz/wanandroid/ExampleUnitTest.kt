package com.cxz.wanandroid

import android.util.Log
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        println("test-floor: ${(-1).div(10)}")
        println("test-floor: ${(-1)/(10)}")
        println("test-floor: ${(-1).floorDiv(10)}")
        println("test-floor: ${(-1).floorMod(10)}")
    }

    private fun Int.floorMod(other: Int): Int = when (other) {
        0 -> this
        else -> this - floorDiv(other) * other
    }
}
