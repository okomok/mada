

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.sequencetest


import mada.sequence._
import junit.framework.Assert._


object _Rec {
   val theFibs = Of(0,1,1,2,3,5,8,13,21,34,55,89,144,233,377, 610,987,1597,2584,4181,6765,10946,17711,28657,46368,75025,121393,196418,317811,514229,832040)
}

class RecTest {

    def testTrivial: Unit = {
        val tr = new Rec[Int]
        tr := Of(1,2,3) ++ tr
        assertEquals(Of(1,2,3,1,2,3,1,2,3,1), tr.take(10))
    }

    def testFibs: Unit = {
        val fibs = new Rec[Int]
        fibs := Of(0, 1) ++ fibs.zipBy(fibs.tail)(_ + _)
        assertEquals(_Rec.theFibs, fibs.take(_Rec.theFibs.size))
    }

}



/*
class RecFibsNoMemoTest extends NoBenchmark {
    override def run = {
        val fibs = new Rec[Int]
        fibs := Of(0, 1) ++ fibs.zipBy(fibs.tail)(_ + _)
        assertEquals(_Rec.theFibs, fibs.take(_Rec.theFibs.size))
    }
}

class RecFibsTest extends NoBenchmark {
    override def run = {
        val fibs = new Rec[Int]
        fibs := (Of(0, 1) ++ fibs.zipBy(fibs.tail)(_ + _)).memoize
        assertEquals(_Rec.theFibs, fibs.take(_Rec.theFibs.size))
    }
}
*/
