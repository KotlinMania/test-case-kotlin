// port-lint: source lib.rs
package io.github.kotlinmania.testcase

/**
 * Provides a parameterized test instance.
 *
 * The upstream testCase attribute macro generates a named test from a
 * function plus one argument set. Kotlin has no common compile-time
 * attribute-macro facility, so the port exposes the same unit of data as a
 * value that a translated test can enumerate explicitly.
 */
public data class TestCase<out A>(
    public val arguments: A,
    public val name: String? = null,
    public val ignored: Boolean = false,
    public val expectedPanic: String? = null,
) {
    public val shouldRun: Boolean
        get() = !ignored

    public fun run(block: (A) -> Unit) {
        if (shouldRun) {
            block(arguments)
        }
    }
}

/**
 * Creates a parameterized test instance.
 *
 * Equivalent to the upstream testCase export.
 */
public fun <A> testCase(
    arguments: A,
    name: String? = null,
    ignored: Boolean = false,
    expectedPanic: String? = null,
): TestCase<A> {
    return TestCase(arguments, name, ignored, expectedPanic)
}
