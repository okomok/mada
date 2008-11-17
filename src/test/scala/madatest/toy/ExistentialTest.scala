
package madatest.toy


import junit.framework.Assert._


import java.lang.Integer
import java.lang.Character


// See: http://www.haskell.org/haskellwiki/Existential_types
class ExistentialTest {

    class Show[T <: AnyRef](val me: T)

    type Obj = Show[T] forSome { type T }
    type Obj2 = Show[_] // also ok.

    def xs: Array[Obj2] = Array(new Show(new Integer(1)), new Show("foo"), new Show(new Character('c')))

    def xsToString: String = {
        val sb = new StringBuilder
        for (x <- xs)
            sb.append(x.me.toString)
        sb.toString
    }

    def testTrivial {
        assertEquals("1fooc", xsToString)
    }
}
