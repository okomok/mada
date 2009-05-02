

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.blend


import mada.Meta
import mada.Blend._
import junit.framework.Assert._


class DoIfTest {

    type on = Meta.`true`
    type off = Meta.`false`

    type id[b <: Meta.Boolean] = b#not#not

    def testOn: Unit = {
        var k = false
        doIf[on] {
            k = true
        }
        assertTrue(k)
    }

    def testOff: Unit = {
        var k = false
        doIf[off] {
            k = true
        }
        assertFalse(k)
    }


    def testOn2: Unit = {
        var k = false
        doIf[id[on]] {
            k = true
        }
        assertTrue(k)
    }

    def testOff2: Unit = {
        var k = false
        doIf[id[off]] {
            k = true
        }
        assertFalse(k)
    }
}
