

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.sequencetest


    import mada.sequence
    import junit.framework.Assert._

    class DocTest {
        def testFibs: Unit = {
            val fibs = new sequence.Recursive[Int]
            fibs := sequence.Of(0, 1) ++ fibs.zipBy(fibs.tail)(_ + _)
            assertEquals(sequence.Of(0,1,1,2,3,5,8,13,21,34), fibs.take(10))
        }
    }
