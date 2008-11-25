
package madatest.toy


import junit.framework.Assert._


class HeteroTest {
    case class Cons[A](val car: A, val cdr: Cons[_])

    case object Nil extends Cons[Any](null, null)

    def testTrivial {
        val l = Cons(1, Cons('a', Cons("abc", Nil)))
        assertEquals("abc", l.cdr.cdr.car)

        l match {
            case Cons(1, Cons('a', Cons("abc", Nil))) => ()
        }
        ()
    }
}
