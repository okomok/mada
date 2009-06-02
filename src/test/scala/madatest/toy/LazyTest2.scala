

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.toy.lazytest2


import junit.framework.Assert._

/*
trait Seq[A] {
    def append(s: => Seq[A]): Seq[A]
    def drop(n: Int): Seq[A]
}

trait SeqForwarder[A] {
    protected def delegate: Seq[A]
    override def append(s: => Seq[A]) = delegate.append(s)
    override def drop(n: Int) = delegate.drop(n)
}

class LazyTest {

    def makeSeq: Seq[Int] = throw new Error

    def useA(a: A): Unit = ()

    def testTrivial: Unit = {
        lazy val s: Seq[Int] = makeSeq append s.drop(10)
        s.drop(3)
    }

}
*/
