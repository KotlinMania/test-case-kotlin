// port-lint: tests test-case/tests/acceptance_tests.rs
package io.github.kotlinmania.testcase

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse

class CaseAliasTest {
    @Test
    fun testCaseAliasConstructor() {
        val tc = case(arguments = "test", name = "custom_name", ignored = false)
        assertEquals("test", tc.arguments)
        assertEquals("custom_name", tc.name)
        assertFalse(tc.ignored)
    }
}
