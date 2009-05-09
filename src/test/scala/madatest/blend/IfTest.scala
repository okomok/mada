

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.blend


import mada.Meta
import mada.Blend._
import junit.framework.Assert._


class IfTest {

    type on = Meta.`true`
    type off = Meta.`false`

    type id[b <: Meta.Boolean] = b#not#not

    def testOn: Unit = {
        var e = false
        val k = `if`[Boolean, on] { true } `else` { e = true; false }
        assertTrue(k)
        assertFalse(e)
    }

    def testOff: Unit = {
        var e = false
        val k = `if`[Boolean, off] { e = true; false } `else` { true }
        assertTrue(k)
        assertFalse(e)
    }

    def testElseIf1: Unit = { // Hmm, dot is needed for elseIf.
        assertEquals(10, `if`[Int, off] { 1 } .elseIf[on] { 10 } `else` { 99 })
        assertEquals(1, `if`[Int, on] { 1 } .elseIf[off] { 10 } .elseIf[off] { 20 } `else` { 99 })
        assertEquals(10, `if`[Int, off] { 1 } .elseIf[on] { 10 } .elseIf[off] { 20 } `else` { 99 })
        assertEquals(20, `if`[Int, off] { 1 } .elseIf[off] { 10 } .elseIf[on] { 20 } `else` { 99 })
        assertEquals(99, `if`[Int, off] { 1 } .elseIf[off] { 10 } .elseIf[off] { 20 } `else` { 99 })
        val k: Int = `if`[Int, off] { 20 } `else`(99)
        assertEquals(99, k)
    }

    def testNotAmbiguous: Unit = {
        val k = `if`[Boolean, on] { true } `else` { false }
        var l = false
        doIf[on] {
            l = true
        }
        assertTrue(k)
        assertTrue(l)
    }

    type _k = Meta._3#increment
    def testWithNat: Unit = {
        import Meta._
        import mada.Blend.`if`
        val n = `if`[scala.Int, _k == _3] { 3 } .elseIf[_k == _4] { 4 } `else` { 5 }
        assertEquals(4, n)
    }

    /*
    // Will never work due to erasure....
    def makeInt0[k <: Meta.Int]: Int = {
        `if`[Int, k#equals[Meta._1]] { 1 } `else` { 99 }
    }

    def makeInt[k <: Meta.Int]: Int = {
        import Meta._
        mada.Blend.`if`[Int, k == _1] { 1 } .elseIf[k == _3] { 3 } .elseIf[k == _7] { 7 } .elseIf[k == _10] { 10 } `else` { 99 }
    }

    def testElseIf2: Unit = {
        assertEquals(1, makeInt[Meta._1])
        assertEquals(3, makeInt[Meta._3])
        assertEquals(7, makeInt[Meta._7])
        assertEquals(10, makeInt[Meta._10])
        assertEquals(99, makeInt[Meta._2])
    }
    */
}
