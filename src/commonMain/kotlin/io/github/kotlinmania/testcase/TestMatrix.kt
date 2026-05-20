// port-lint: source src/lib.rs
package io.github.kotlinmania.testcase

/**
 * Generates test cases from the Cartesian product of argument values.
 *
 * Equivalent to the upstream `test_matrix` export.
 */
public fun <A> testMatrix(first: Iterable<A>): List<TestCase<A>> =
    first.map { a -> testCase(a) }

/**
 * Generates test cases from the Cartesian product of two argument axes.
 */
public fun <A, B> testMatrix(
    first: Iterable<A>,
    second: Iterable<B>,
): List<TestCase<Pair<A, B>>> =
    first.flatMap { a ->
        second.map { b -> testCase(a to b) }
    }

/**
 * Generates test cases from the Cartesian product of three argument axes.
 */
public fun <A, B, C> testMatrix(
    first: Iterable<A>,
    second: Iterable<B>,
    third: Iterable<C>,
): List<TestCase<Triple<A, B, C>>> =
    first.flatMap { a ->
        second.flatMap { b ->
            third.map { c -> testCase(Triple(a, b, c)) }
        }
    }
