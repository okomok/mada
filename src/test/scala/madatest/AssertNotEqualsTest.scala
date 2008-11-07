
package madatest


import junit.framework.Assert._
import junit.framework.AssertionFailedError


class AssertNotEqualsTest {
    def testThis {
        AssertNotEquals("abc", "efg")
        AssertNotEquals("wowow", 21, 20)

        var thrown =
            try {
                AssertNotEquals(20, 20); false
            } catch {
                case _: AssertionFailedError => true
            }
        assertTrue(thrown)

        thrown =
            try {
                AssertNotEquals("abc", "abc"); false
            } catch {
                case _: AssertionFailedError => true
            }
        assertTrue(thrown)
    }
}
