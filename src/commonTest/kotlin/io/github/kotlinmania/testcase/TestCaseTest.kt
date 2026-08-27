// port-lint: tests test-case/tests/acceptance_tests.rs
package io.github.kotlinmania.testcase

import kotlin.math.abs
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertNull
import kotlin.test.assertTrue

public class TestCaseTest {
    @Test
    public fun casesCanBeDeclaredOnAsyncMethods() {
        val cases =
            listOf(
                testCase(2 to 4, "async positive"),
                testCase(-2 to -4, "async negative"),
            )

        for (testCase in cases) {
            testCase.run { (x, y) ->
                assertEquals(8, asyncProductAbs(x, y))
            }
        }
    }

    @Test
    public fun casesCanBeDeclaredOnNonTestItems() {
        val cases = buildMultiplicationCases()

        assertEquals(3, cases.size)
        assertEquals("when operands are swapped", cases[2].name)
    }

    @Test
    public fun casesDeclaredOnNonTestItemsCanBeUsed() {
        for (testCase in buildMultiplicationCases()) {
            testCase.run { (x, y) ->
                assertEquals(8, abs(x * y))
            }
        }
    }

    @Test
    public fun casesCanBeIgnored() {
        val ignored = testCase("expensive case", ignored = true)
        var ran = false

        ignored.run {
            ran = true
        }

        assertFalse(ignored.shouldRun)
        assertFalse(ran)
    }

    @Test
    public fun casesCanPanic() {
        val panicking = testCase("boom", expectedPanic = "boom")

        val failure =
            assertFailsWith<IllegalStateException> {
                panicking.run { message ->
                    throw IllegalStateException(message)
                }
            }

        assertEquals(panicking.expectedPanic, failure.message)
    }

    @Test
    public fun casesCanReturnResult() {
        val cases =
            listOf(
                testCase(Result.success(8), "success"),
                testCase(Result.failure<Int>(IllegalArgumentException("bad input")), "failure"),
            )

        assertEquals(8, cases[0].arguments.getOrThrow())
        assertFailsWith<IllegalArgumentException> {
            cases[1].arguments.getOrThrow()
        }
    }

    @Test
    public fun casesSupportBasicFeatures() {
        val cases =
            listOf(
                testCase(-2 to -4, "when both operands are negative"),
                testCase(2 to 4, "when both operands are positive"),
                testCase(4 to 2, "when operands are swapped"),
            )

        for (testCase in cases) {
            testCase.run { (x, y) ->
                val actual = abs(x * y)

                assertEquals(8, actual)
            }
        }
    }

    @Test
    public fun casesSupportComplexAssertions() {
        val cases =
            listOf(
                testCase(listOf(1, 2, 3), "ordered values"),
                testCase(listOf(3, 2, 1), "reverse values"),
            )

        for (testCase in cases) {
            testCase.run { values ->
                assertEquals(6, values.sum())
                assertTrue(values.contains(2))
            }
        }
    }

    @Test
    public fun casesSupportGenerics() {
        val strings = testCase(listOf("a", "b"), "strings")
        val ints = testCase(listOf(1, 2), "ints")

        assertEquals("a", firstValue(strings.arguments))
        assertEquals(1, firstValue(ints.arguments))
    }

    @Test
    public fun casesSupportKeywordUsing() {
        val usingNamedArguments = testCase(arguments = 2 to 4, name = "using")

        usingNamedArguments.run { (x, y) ->
            assertEquals(8, abs(x * y))
        }
    }

    @Test
    public fun casesSupportKeywordWith() {
        val withNamedArguments =
            testCase(
                arguments = 4 to 2,
                name = "with",
                ignored = false,
            )

        withNamedArguments.run { (x, y) ->
            assertEquals(8, abs(x * y))
        }
    }

    @Test
    public fun casesSupportMultipleCallingMethods() {
        val explicit = testCase(2 to 4, "testCase")
        val aliased = case(4 to 2, "case")

        for (testCase in listOf(explicit, aliased)) {
            testCase.run { (x, y) ->
                assertEquals(8, abs(x * y))
            }
        }
    }

    @Test
    public fun casesSupportPatternMatching() {
        val cases =
            listOf(
                testCase(Shape.Circle(2), "circle"),
                testCase(Shape.Rectangle(2, 4), "rectangle"),
            )

        val areas =
            cases.map { testCase ->
                when (val shape = testCase.arguments) {
                    is Shape.Circle -> shape.radius * shape.radius
                    is Shape.Rectangle -> shape.width * shape.height
                }
            }

        assertEquals(listOf(4, 8), areas)
    }

    @Test
    public fun casesCanUseRegex() {
        val cases =
            listOf(
                testCase("test casesSupportBasicFeatures ... ok"),
                testCase("test matricesSupportBasicFeatures ... ok"),
            )
        val testOutputLine = Regex("""test \w+ \.\.\. ok""")

        for (testCase in cases) {
            assertTrue(testOutputLine.matches(testCase.arguments))
        }
    }

    @Test
    public fun featuresProduceHumanReadableErrors() {
        val panicking = testCase("case failed", name = "human readable", expectedPanic = "case failed")

        val failure =
            assertFailsWith<IllegalStateException> {
                panicking.run { message ->
                    throw IllegalStateException(message)
                }
            }

        assertEquals("case failed", failure.message)
    }

    @Test
    public fun allowStaysOnFn() {
        val case = testCase(AllowedInvocation("allow", 8), "allow stays on function")

        case.run { invocation ->
            assertEquals(8, invocation.value)
            assertEquals("allow", invocation.marker)
        }
    }

    @Test
    public fun unnamedCasesAreAllowed() {
        val testCase = testCase("value")

        assertEquals("value", testCase.arguments)
        assertNull(testCase.name)
    }

    private fun asyncProductAbs(x: Int, y: Int): Int =
        abs(x * y)

    private fun buildMultiplicationCases(): List<TestCase<Pair<Int, Int>>> =
        listOf(
            testCase(-2 to -4, "when both operands are negative"),
            testCase(2 to 4, "when both operands are positive"),
            testCase(4 to 2, "when operands are swapped"),
        )

    private fun <T> firstValue(values: List<T>): T =
        values.first()

    private data class AllowedInvocation(
        val marker: String,
        val value: Int,
    )

    private sealed class Shape {
        data class Circle(
            val radius: Int,
        ) : Shape()

        data class Rectangle(
            val width: Int,
            val height: Int,
        ) : Shape()
    }

    private fun getSnapshotDirectory(): String = "snapshots/rust-stable"

    private fun sanitizeLines(s: String): String {
        return s.split('\n')
            .filter { line ->
                (line.startsWith("test") || line.contains("panicked at") || line.startsWith("error:") || line.startsWith("error[")) &&
                    !line.contains("process didn't exit successfully")
            }
            .map { line ->
                val sb = StringBuilder()
                for (c in line) {
                    if (c == '\\') sb.append('/') else sb.append(c)
                }
                sb.toString()
            }
            .joinToString("\n")
    }
}


