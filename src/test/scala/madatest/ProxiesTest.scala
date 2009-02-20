

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest


import junit.framework.Assert._


class ProxiesVarTest {
    import mada.Proxies._

    def assign(v: Var[Int]) {
        v := 12
    }

    def testTrivial {
        var x = 10
        val v = new Var(x)
        assign(v)
        x = v.self
        assertTrue(x == 12)
    }

    def testTrivial2 {
        var x = 10
        val v = new Var(x)
        assign(v)
        x = v.self
        assertTrue(x == 12)
    }

    def testExtract: Unit = {
        val s = "abc"
        val r = new Var(s)
        val Mutable(p) = r
        assertSame(p, s)

        r match {
            case Mutable.Empty() => fail("doh")
            case Mutable(p) => assertSame(s, p)
            case _ => fail("doh")
        }
        ()
    }

    def testExtractEmpty: Unit = {
        val r = new Var[String]
        r match {
            case Mutable(p) => fail("doh")
            case Mutable.Empty() => ()
            case _ => fail("doh")
        }
    }

    def testSwap: Unit = {
        val v = new Var[Int](3)
        val w = new Var[Int](5)
        v proxySwap w
        assertEquals(5, v.self)
        assertEquals(3, w.self)
    }
}
