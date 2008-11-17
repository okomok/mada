
package madatest.toy


import junit.framework.Assert._


// See: http://www.haskell.org/ghc/docs/latest/html/users_guide/data-type-extensions.html#gadt
class GADTsTest {
    class Term[A]
    case class Lit(i: Int) extends Term[Int]
    case class Succ(t: Term[Int]) extends Term[Int]
    case class IsZero(t: Term[Int]) extends Term[Boolean]
    case class If[A](b: Term[Boolean], e1: Term[A], e2: Term[A]) extends Term[A]
    case class Pair[A, B](e1: Term[A], e2: Term[B]) extends Term[(A, B)]

    def eval[A](t: Term[A]): A = t match {
        case Lit(i) => i
        case Succ(t) => 1 + eval(t)
        case IsZero(t) => eval(t) == 0
        case If(b, e1, e2) => if (eval(b)) eval(e1) else eval(e2)
        case Pair(e1, e2) => (eval(e1), eval(e2))
    }

    def testTrivial {
        assertEquals(2, eval(Succ(Lit(1))))
    }
}
