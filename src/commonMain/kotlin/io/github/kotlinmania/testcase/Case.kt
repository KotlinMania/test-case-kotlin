// port-lint: source lib.rs
package io.github.kotlinmania.testcase

/**
 * Alias constructor for a parameterized test instance.
 *
 * Equivalent to the upstream case export, which is a renamed export of
 * the same macro as [testCase].
 */
public fun <A> case(
    arguments: A,
    name: String? = null,
    ignored: Boolean = false,
    expectedPanic: String? = null,
): TestCase<A> {
    return testCase(arguments, name, ignored, expectedPanic)
}
