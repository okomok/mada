

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest


import junit.framework.Assert._


class VarTest {
    import mada.Var

    def assign(v: Var[Int]) {
        v := 12
    }

    def testTrivial {
        var x = 10
        val v = new Var(x)
        assign(v)
        x = ~v
        assertTrue(x == 12)
    }

    def testTrivial2 {
        var x = 10
        val v = new Var(x)
        assign(v)
        x = ~v
        assertTrue(x == 12)
    }

    def testTrivial3 {
        val v = new Var(12)
        assertEquals(12, ~v)
        assertTrue(v)

        v := 13
        assertEquals(13, ~v)
        assertTrue(v)
        v.resign
        assertTrue(!v)
        assertTrue(!v)

        v assign 14
        assertEquals(14, ~v)
        assertTrue(v)
        v := null
        assertTrue(!v)
    }

    def testSwap: Unit = {
        val v = new Var[Int](3)
        val w = new Var[Int](5)
        v swap w
        assertEquals(5, ~v)
        assertEquals(3, w.ref)
    }

    def testOption = {
        val v: Var[Int] = None
        assertFalse(v)
        val w: Var[Int] = Some(10)
        assertEquals(10, ~w)

        val o: Option[Int] = new Var[Int]
        assertTrue(o.isEmpty)

        val c: Option[Int] = new Var(10)
        assertEquals(10, c.get)
    }

    def testNull = {
        val v = new Var[String](null)
        assertTrue(v)
    }

}
