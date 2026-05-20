// port-lint: source src/lib.rs
package io.github.kotlinmania.testcase

/**
 * Alias constructor for a parameterized test instance.
 *
 * Equivalent to the upstream `case` export, which is a renamed export of
 * the same macro as [testCase].
 */
public fun <A> case(arguments: A, name: String? = null): TestCase<A> =
    testCase(arguments, name)
