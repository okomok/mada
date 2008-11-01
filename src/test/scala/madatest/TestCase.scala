
package madatest.range


trait TestCase {
    private var assertions: Int = 0
    private var failures: Int = 0
    private var message = new StringBuilder

    protected def assertTrue(msg: String, cond: Boolean) {
        assertions = assertions + 1
        if (!cond) {
            message.append("    <failed>" ++ msg ++ "</failed>\n")
            failures = failures + 1
        }
    }

    protected def assertTrue(cond: Boolean) {
        assertTrue(new StringBuilder("at: ").append(assertions.toString).toString, cond)
    }

    protected def applyTest: Unit

    def testThis {
        message.append("  <test>\n")
        applyTest
        message.append("    <assertions>" ++ assertions.toString ++ "</assertions>\n")
        message.append("    <failures>" ++ failures.toString ++ "</failures>\n")
        message.append("  </test>")
        if (failures != 0) {
            println(message.toString)
            throw new junit.framework.AssertionFailedError()
        }
    }
}
