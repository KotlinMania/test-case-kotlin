// port-lint: source tests/acceptance_tests.rs
package io.github.kotlinmania.testcase

import kotlin.math.abs
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

public class TestCaseTest {
    @Test
    public fun casesSupportBasicFeatures() {
        val cases = listOf(
            testCase(-2 to -4, "when both operands are negative"),
            testCase(2 to 4, "when both operands are positive"),
            testCase(4 to 2, "when operands are swapped"),
        )

        for (testCase in cases) {
            val (x, y) = testCase.arguments
            val actual = abs(x * y)

            assertEquals(8, actual)
        }
    }

    @Test
    public fun casesSupportMultipleCallingMethods() {
        val explicit = testCase(2 to 4, "testCase")
        val aliased = case(4 to 2, "case")

        for (testCase in listOf(explicit, aliased)) {
            val (x, y) = testCase.arguments
            assertEquals(8, abs(x * y))
        }
    }

    @Test
    public fun unnamedCasesAreAllowed() {
        val testCase = testCase("value")

        assertEquals("value", testCase.arguments)
        assertNull(testCase.name)
    }
}
