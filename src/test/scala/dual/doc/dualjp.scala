

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest
package dualtest; package doctest; package dualjp


import com.github.okomok.mada
import mada.dual
import junit.framework.Assert.assertEquals


class DualityTest extends org.scalatest.junit.JUnit3Suite {

    def myAssert(a: dual.`true`) = ()

    def testDuality {
        import dual.nat.peano.Literal._
        val i: _3 = _3
        val j: _5#minus[_2] = _5.minus(_2)
        myAssert(i.equal(j)) // compile-time check
    }


    class PrintSquare1[n <: dual.Nat] {
        type i = n#times[n]
        //val a: scala.Int = ???
        //println(a)
    }

    class PrintSquare2[n <: dual.Nat](n: n) {
        val a: scala.Int = n.times(n).undual
        println(a)
    }

}



class FibonacciTest1 {

    import dual.nat.peano.Literal._

    type fibonacci[n <: dual.Nat] = dual.`if`[n#lt[_2], dual.const0[n], FibElse[n]]#apply#asNat

    trait FibElse[n <: dual.Nat] extends dual.Function0 {
        type self = FibElse[n]
        override type apply = fibonacci[n#decrement]#plus[fibonacci[n#decrement#decrement]]
    }

}

class FibonacciTest2 {

    import dual.nat.peano.Literal._

    type fibonacci[n <: dual.Nat] = dual.`if`[n#lt[_2], dual.const0[n], FibElse[n]]#apply#asNat

    trait FibElse[n <: dual.Nat] extends dual.Function0 {
        type self = FibElse[n]
        override type apply = fibonacci[n#minus[_1]]#plus[fibonacci[n#decrement#decrement]]
    }

}


class ImplementationProblemTest {

    trait MyMetatype extends dual.Any {
        type foo <: dual.Nat
        type bar = foo#plus[foo] // !!?
    }

    class Outer[m <: dual.Nat](m: m) {
        type apply[n <: dual.Nat] = Nothing

        abstract class Inner[n <: dual.Nat](n: n) extends dual.Function0 {
            type self = Inner[n]
            override type apply = Nothing
        }
    }

}



class ListTest extends org.scalatest.junit.JUnit3Suite {

    import dual.nat.peano.Literal._

    object add2 extends dual.Function1 {
        override type self = add2.type
        override  def apply[x <: dual.Any](x: x): apply[x] = x.asNat plus _2
        override type apply[x <: dual.Any] = x#asNat#plus[_2]
    }

    def testTrivial {
        val xs = _3 :: _4 :: _5 :: _6 :: dual.Nil
        val u = xs.map(add2) // returns a view
        assertEquals(5 :: 6 :: 7 :: 8 :: Nil, u.undual)
        locally {
            import dual.::
            val v: _5 :: _6 :: _7 :: _8 :: dual.Nil = u.force
        }
    }

}


class NormalTest extends org.scalatest.junit.JUnit3Suite {

    import dual.nat.peano.Literal._

    def testUndual {
        val i: scala.Int = _3.times(_2).undual
        assertEquals(6, i)
    }

    def testBox {
        val j = dual.Tuple2(dual.Box(2), dual.Box(3))
        assertEquals(3, j._2.undual)
    }

    def testLift {
        val j = dual.Tuple(2, 30) // == Tuple2(Box(2), Box(3))
        val xs = 3 #:: 4 #:: 5 #:: dual.Nil // == Box(3) :: Box(4) :: Box(3) :: Nil
        val y = xs.foldLeft(j._1, dual.Function((y: Int, x: Int) => y + x))
        assertEquals(2+3+4+5, y.undual)
    }

}
