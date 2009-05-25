

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.blendtest


    import mada.meta.literal.nat._
    import mada.blend._
    import junit.framework.Assert._

    class DocTest {
        def testTrivial: Unit = {
            type l = String :: Boolean :: Char :: Int :: Nil
            val l: l = "hetero" :: true :: 'L' :: 7 :: Nil
            val i: l#at[_3N] = l.at[_3N]
            assertEquals(10, i + 3)
        }
    }
