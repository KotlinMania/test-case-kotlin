#if canImport(Testing)
import Testing
import TestCase

@Suite("TestCase Swift Export Smoke Tests")
struct TestCaseExportTests {
    @Test("Swift module loads and basic testCase runs")
    func swiftModuleLoads() {
        let tc = TestCase_kt.testCase(arguments: "hello", name: "test1", ignored: false, expectedPanic: nil)
        #expect(tc.name == "test1")
        #expect(tc.shouldRun)
    }
}
#elseif canImport(XCTest)
import XCTest
import TestCase

final class TestCaseExportTests: XCTestCase {
    func testSwiftModuleLoads() throws {
        let tc = TestCase_kt.testCase(arguments: "hello", name: "test1", ignored: false, expectedPanic: nil)
        XCTAssertEqual(tc.name, "test1")
        XCTAssertTrue(tc.shouldRun)
    }
}
#endif
