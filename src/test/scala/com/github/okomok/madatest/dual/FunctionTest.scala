

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package dualtest


import com.github.okomok.mada.dual._
import junit.framework.Assert._


class FunctionTest extends junit.framework.TestCase {

    final case class Plus() extends Function2 {
        override  val self = this
        override type self = Plus
        override  def apply[n <: Any, m <: Any](n: n, m: m): apply[n, m] = n.asInstanceOfNat + m.asInstanceOfNat
        override type apply[n <: Any, m <: Any] = n#asInstanceOfNat# + [m#asInstanceOfNat]
    }

    def testCurried {
        type c = Plus#curried
        val c: c = Plus().curried

        type a = c#apply[_3N]
        val a: a = c.apply(_3N)

        type b = a#apply[_2N]
        val b: b = a.apply(_2N)

        val z: _5N = b
        assert(b === _5N)
        val d: c#apply[_4N]#apply[_5N] = c(_4N)(_5N)
        assert(d === _9N)
    }

    def testTupled {
        type c = Plus#tupled
        val c: c = Plus().tupled

        type k = c#apply[Tuple2[_3N, _4N]]
        val k/* : k */ = c.apply(Tuple2(_3N, _4N))//.asInstanceOf[k]// still doesn't compile!

        val r: _7N = k
        assert(k === _7N)
        ()
    }

    final case class Plus2() extends Function1 {
        override  val self = this
        override type self = Plus2
        override  def apply[n <: Any](n: n): apply[n] = n.asInstanceOfNat + _2N
        override type apply[n <: Any] = n#asInstanceOfNat# + [_2N]
    }

    final case class Minus3() extends Function1 {
        override  val self = this
        override type self = Minus3
        override  def apply[n <: Any](n: n): apply[n] = n.asInstanceOfNat - _3N
        override type apply[n <: Any] = n#asInstanceOfNat# - [_3N]
    }

    def testCompose {
        type c = Plus2#compose[Minus3]
        val c: c = Plus2().compose(Minus3())
        val r/*: c#apply[_5N]*/ = c.apply(_5N) // hmm...
        assert(r === _4N)
        val k: _4N = r
        ()
    }

    def testAndThen {
        val k: _3N = (Minus3() andThen Plus2())(_4N)
        try {
            Minus3().andThen(Plus2()).apply(_2N)
            fail("never come here")
        } catch {
            case _: UnsupportedOperationException =>
        }
        ()
    }
}
