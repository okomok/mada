

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest


import mada.Meta
import mada.Blend
import junit.framework.Assert._


class IfTest {

    type on = Meta.`true`
    type off = Meta.`false`

    type id[b <: Meta.Boolean] = b#not#not

    import Blend.If._

    def testOn: Unit = {
        var k = false
        Blend.`if`[on] {
            k = true
        }
        assertTrue(k)
    }

    def testOff: Unit = {
        var k = false
        Blend.`if`[off] {
            k = true
        }
        assertFalse(k)
    }


    def testOn2: Unit = {
        var k = false
        Blend.`if`[id[on]] {
            k = true
        }
        assertTrue(k)
    }

    def testOff2: Unit = {
        var k = false
        Blend.`if`[id[off]] {
            k = true
        }
        assertFalse(k)
    }
}
