

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.blend


import mada.Blend._
import mada.Meta


class ListTest {
    def testAt: Unit = {
        import junit.framework.Assert._
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

    trait testAt {
        import Meta.assertSame
        type lst = Int :: String :: Double :: Char :: Nil
        assertSame[lst#at[Meta._0I], Int]
        assertSame[lst#at[Meta._1I], String]
        assertSame[lst#at[Meta._2I], Double]
        assertSame[lst#at[Meta._3I], Char]
        assertSame[lst#at[Meta._2I#add[Meta._1I]], Char]
    }
}
