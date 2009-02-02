

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest


import junit.framework.Assert._


class ProxiesRefTest {
    import mada.Proxies.Ref

    def assign(v: Ref[Int]) {
        v := 12
    }

    def testTrivial {
        var x = 10
        val v = new Ref(x)
        assign(v)
        x = v.self
        assertTrue(x == 12)
    }

    def testTrivial2 {
        var x = 10
        val v = new Ref(x)
        assign(v)
        x = v.self
        assertTrue(x == 12)
    }

    def testExtract: Unit = {
        val s = "abc"
        val r = Ref(s)
        val Ref(p) = r
        assertSame(p, s)

        r match {
            case Ref(p) => assertSame(s, p)
            case _ => fail("doh")
        }
        ()
    }
/* rejected
    def testExtractNull: Unit = {
        val r = new Ref[String]
        var failed = false
        r match {
            case Ref(p) => fail("doh")
            case _ => failed = true
        }
        assertTrue(failed)
    }
*/
}
