
package madatest


import junit.framework.Assert._
import junit.framework.AssertionFailedError


class AssertImpliesTest {
    def testThis {
        AssertImplies(true, true)
        AssertImplies(false, true)
        AssertImplies(false, false)

        var thrown =
            try {
                AssertImplies(true, false); false
            } catch {
                case _: AssertionFailedError => true
            }
        assertTrue(thrown)
    }
}
