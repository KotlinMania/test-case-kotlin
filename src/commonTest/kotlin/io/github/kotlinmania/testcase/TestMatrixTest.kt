// port-lint: tests tests/acceptance_tests.rs
package io.github.kotlinmania.testcase

import kotlin.math.abs
import kotlin.test.Test
import kotlin.test.assertEquals

public class TestMatrixTest {
    @Test
    public fun matricesSupportBasicFeatures() {
        val cases =
            testMatrix(
                listOf(-2, 2),
                listOf(-4, 4),
            )

        assertEquals(4, cases.size)
        for (testCase in cases) {
            val (x, y) = testCase.arguments
            assertEquals(8, abs(x * y))
        }
    }

    @Test
    public fun matricesSupportConstantArguments() {
        val cases =
            testMatrix(
                listOf(-2, 2),
                listOf(4),
                listOf("constant"),
            )

        assertEquals(2, cases.size)
        for (testCase in cases) {
            val (x, y, label) = testCase.arguments
            assertEquals(8, abs(x * y))
            assertEquals("constant", label)
        }
    }

    @Test
    public fun matricesCompilationErrors() {
        val emptyAxis =
            testMatrix(
                listOf(1, 2),
                emptyList<Int>(),
            )

        assertEquals(0, emptyAxis.size)
    }
}
