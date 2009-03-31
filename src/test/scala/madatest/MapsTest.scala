

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest


import mada.Maps
import mada.Functions
import junit.framework.Assert._


class MapsTest {
    def testLazyGet: Unit = {
        val map = new java.util.concurrent.ConcurrentHashMap[String, () => Int]
        assertEquals( 3, Maps.lazyGet(map)("abc"){3} )
        assertEquals( 3, Maps.lazyGet(map)("abc"){fail("doh"); 3} )

        assertEquals( 5, Maps.lazyGet(map)("abcde"){5} )
        assertEquals( 5, Maps.lazyGet(map)("abcde"){fail("doh"); 5} )
    }
}
