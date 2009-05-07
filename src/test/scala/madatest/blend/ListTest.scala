

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.blend


import mada.Blend._
import mada.Meta


class ListUnmetaTest {
    import junit.framework.Assert._

    def testAt = {
        val i = new java.lang.Integer(10)
        val lst = 3 :: "hello" :: i :: 'a' :: Nil
        val _0: Int = lst.at[Meta._0I]
        assertEquals(3, _0)
        val _1: String = lst.at[Meta._1I]
        assertEquals("hello", _1)
        val _2: java.lang.Integer = lst.at[Meta._2I]
        assertSame(i, _2)
        val _3: Char = lst.at[Meta._3I]
        assertEquals('a', _3)
        assertEquals(10, lst.at[Meta._2I].intValue)
    }

    def testLength = {
        val i = new java.lang.Integer(10)
        val lst = 3 :: "hello" :: i :: 'a' :: Nil
        assertEquals(4, lst.length)
    }
}

class ListMetaTest {
    import Meta.{ assert, assertSame }

    trait testAt {
        type lst = Int :: String :: Double :: Char :: Nil
        assertSame[lst#at[Meta._0I], Int]
        assertSame[lst#at[Meta._1I], String]
        assertSame[lst#at[Meta._2I], Double]
        assertSame[lst#at[Meta._3I], Char]
        assertSame[lst#at[Meta._2I#add[Meta._1I]], Char]
    }

    trait testLength {
        type lst = Int :: String :: Double :: Char :: Nil
        assert[lst#length#equals[Meta._4I]]
    }
}
