

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package dualtest


import com.github.okomok.mada
import mada.dual._
import mada.dual.nat.peano.Literal._
import junit.framework.Assert._


class FunctionTest extends org.scalatest.junit.JUnit3Suite {

    final case class Plus() extends Function2 {
        override  val self = this
        override type self = Plus
        override  def apply[n <: Any, m <: Any](n: n, m: m): apply[n, m] = n.asInstanceOfNat + m.asInstanceOfNat
        override type apply[n <: Any, m <: Any] = n#asInstanceOfNat# + [m#asInstanceOfNat]
    }

    def testCurried {
        type c = Plus#curried
        val c: c = Plus().curried

        type a = c#apply[_3]
        val a: a = c.apply(_3)

        type b = a#apply[_2]
        val b: b = a.apply(_2)

        val z: _5 = b
        mada.dual.assert(b === _5)
        val d: c#apply[_4]#apply[_5] = c(_4)(_5)
        mada.dual.assert(d === _9)
    }

    def testTupled {
        type c = Plus#tupled
        val c: c = Plus().tupled

        type k = c#apply[Tuple2[_3, _4]]
        val k : k = c.apply(Tuple2(_3, _4))

        val r: _7 = k
        mada.dual.assert(k === _7)
        ()
    }

    final case class Plus2() extends Function1 {
        override  val self = this
        override type self = Plus2
        override  def apply[n <: Any](n: n): apply[n] = n.asInstanceOfNat + _2
        override type apply[n <: Any] = n#asInstanceOfNat# + [_2]
    }

    final case class Minus3() extends Function1 {
        override  val self = this
        override type self = Minus3
        override  def apply[n <: Any](n: n): apply[n] = n.asInstanceOfNat - _3
        override type apply[n <: Any] = n#asInstanceOfNat# - [_3]
    }

    def testCompose {
        type c = Plus2#compose[Minus3]
        val c: c = Plus2().compose(Minus3())
        val r: c#apply[_5] = c.apply(_5)
        mada.dual.assert(r === _4)
        val k: _4 = r
        ()
    }

    def testAndThen {
        val k: _3 = (Minus3() andThen Plus2())(_4)
        try {
            Minus3().andThen(Plus2()).apply(_2)
            fail("never come here")
        } catch {
            case _: UnsupportedOperationException =>
        }
        ()
    }
}
