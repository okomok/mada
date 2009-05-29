

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.sequencetest


import mada.sequence._
import junit.framework.Assert._


object _Recursive {
   val theFibs = Of(0,1,1,2,3,5,8,13,21,34)
   val theLongFibs = Of(0,1,1,2,3,5,8,13,21,34,55,89,144,233,377, 610,987,1597,2584,4181,6765,10946,17711,28657,46368,75025,121393,196418,317811,514229,832040)
}

class RecursiveTest {

    def testTrivial: Unit = {
        val tr = new Recursive[Int]
        tr := Of(1,2,3) ++ tr // infinite
        assertEquals(Of(1,2,3,1,2,3,1,2,3,1), tr.take(10))
    }

    def testTrivial2: Unit = {
        val tr = new Recursive[Int]
        tr := Of(1,2,3) ++ tr.take(2) // finite
        assertEquals(Of(1,2,3,1,2), tr)
    }

    def testTrivial3: Unit = {
        val tr = new Recursive[Int]
        tr := Of(1,2,3) ++ tr.takeWhile(_ != 3) // finite
        assertEquals(Of(1,2,3,1,2), tr)
    }

    def testFibs: Unit = {
        val fibs = new Recursive[Int]
        fibs := Of(0, 1) ++ fibs.zipBy(fibs.tail)(_ + _)
        assertEquals(_Recursive.theFibs, fibs.take(_Recursive.theFibs.size))
    }

    def testFibsIndirect: Unit = {
        val fibs, fibs_tail = new Recursive[Int]
        fibs := Of(0, 1) ++ fibs.zipBy(fibs_tail)(_ + _)
        fibs_tail := fibs.tail
        assertEquals(_Recursive.theFibs.step(3), fibs.take(_Recursive.theFibs.size).step(3))
    }

    def testLongFibs: Unit = {
        val fibs = new Infinite[Int]()
        fibs := Of(0, 1) ++ fibs.zipBy(fibs.tail)(_ + _)
        assertEquals(_Recursive.theLongFibs, fibs.take(_Recursive.theLongFibs.size))
    }
/*
    def testInfinitize: Unit = {
        val tr = new Recursive[Int]
        tr := Of(1,2,3) ++ tr.take(2) // finite
        println(Infinitize(tr).take(30))
    }

    def testInfinitize0: Unit = {
        println(Infinitize(emptyOf[Int]).take(30))
    }
*/
}



/*
class RecursiveFibsNoMemoTest extends NoBenchmark {
    override def run = {
        val fibs = new Recursive[Int]
        fibs := Of(0, 1) ++ fibs.zipBy(fibs.tail)(_ + _)
        assertEquals(_Recursive.theFibs, fibs.take(_Recursive.theFibs.size))
    }
}

class RecursiveFibsTest extends NoBenchmark {
    override def run = {
        val fibs = new Recursive[Int]
        fibs := (Of(0, 1) ++ fibs.zipBy(fibs.tail)(_ + _)).memoize
        assertEquals(_Recursive.theFibs, fibs.take(_Recursive.theFibs.size))
    }
}
*/
