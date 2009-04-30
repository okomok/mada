

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest


import mada.Meta
import mada.DoIf
import junit.framework.Assert._


class DoIfTest {

    type on = Meta.`true`
    type off = Meta.`false`

    type id[b <: Meta.Boolean] = b#not#not

    import DoIf._

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
