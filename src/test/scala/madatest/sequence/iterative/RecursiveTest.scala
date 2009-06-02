

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.sequencetest.iterativetest


import mada.sequence.iterative._
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
        val tr = new Recursive[Int] // any override is ignored.
        tr := Of(1,2,3) ++ tr.take(2) // finite
        assertEquals(Of(1,2,3,1,2), tr)
    }

    def testTrivialForwarder: Unit = {
        val tr = new RecursiveForwarder[Int] // overrides are valid, but needs byLazy.
        tr := Of(1,2,3) ++ byLazy(tr.take(2))
        assertEquals(Of(1,2,3,1,2), tr)
    }

    def testTrivialLazy: Unit = {
        lazy val tr: Type[Int] = Of(1,2,3) ++ byLazy(tr.take(2)) // same as above.
        assertEquals(Of(1,2,3,1,2), tr)
    }
/*
    malformed.
    def testTrivialVal1: Unit = {
        val tr: Type[Int] = byLazy(Of(1,2,3) ++ tr.take(2))
        assertEquals(Of(1,2,3,1,2), tr)
    }
    def testTrivialVal2: Unit = {
        val tr :Type[Int] = Of(1,2,3) ++ byLazy(tr) // finite
        assertEquals(Of(1,2,3,1,2), tr)
    }
*/
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
    def testLongFibs2: Unit = {
        val fibs = new Infinite[Int]()
        // hmm, delayed-begin trick doesn't work.
        // After all, iterator object too must be lazy: infeasible in iterator abstraction.
        fibs := Of(0, 1) ++ fibs.takeWhile(_ => true).zipBy(fibs.takeWhile(_ => true).tail)(_ + _)
        assertEquals(_Recursive.theLongFibs, fibs.take(_Recursive.theLongFibs.size))
    }
*/
/*
    def testStream: Unit = {
        // This is the bug of 2.8.
        // Iterator eagerly evaluates stream-tail.
        lazy val fib: Stream[Int] = Stream.cons(0, Stream.cons(1, fib.zip(fib.tail).map(p => p._1 + p._2)))
        println("go")
        for (e <- fib.take(30)) {
            println(e)
        }
    }
*/
/*
    def testStream2: Unit = {
       def from(n: Int): Stream[Int] =
         Stream.cons(n, from(n + 1))

       def sieve(s: Stream[Int]): Stream[Int] =
         Stream.cons(s.head, sieve(s.tail filter { _ % s.head != 0 }))

       def primes = sieve(from(2))

       primes take 10 print
    }
*/
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
class FibTest {

    def myZip[A, B](_this: Stream[A], that: Stream[B]): Stream[(A, B)] = {
        if (_this.isEmpty || that.isEmpty) Stream.empty
        else Stream.cons((_this.head, that.head), myZip(_this.tail, that.tail))
    }

    lazy val fibs: Stream[Int] = Stream.cons(1, Stream.cons(1, myZip(fibs, fibs.tail).map{case (x, y) => x + y }))

    def testTrivial: Unit = {
        for (e <- fibs.take(30)) {
            println(e)
        }
    }
}
*/


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
