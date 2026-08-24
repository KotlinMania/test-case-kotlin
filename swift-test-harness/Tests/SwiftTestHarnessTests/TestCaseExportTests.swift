import Testing
import TestCase

@Suite("TestCase Swift Export Suite")
struct TestCaseExportTests {
    @Test("Swift module loads and basic testCase runs")
    func swiftModuleLoads() {
        let tc = TestCase(arguments: nil, name: "test1", ignored: false, expectedPanic: nil)
        #expect(tc.name == "test1")
        #expect(tc.shouldRun)
    }
}
