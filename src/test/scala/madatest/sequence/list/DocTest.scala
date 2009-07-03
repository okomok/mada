

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.sequencetest.listtest


    import mada.sequence._
    import junit.framework.Assert._

    class DocTest {
        def testTrivial: Unit = {
            lazy val fibs: List[Int] = 0 :: 1 :: fibs.zipBy(fibs.tail)(_ + _)
            assertEquals(832040, fibs.nth(30))
        }
    }
