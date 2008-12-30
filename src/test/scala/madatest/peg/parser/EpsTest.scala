

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.peg.parser


import mada.peg.Parser._
import mada.peg._
import junit.framework.Assert._


class EpsTest {
    def testTrivial: Unit = {
        ()
    }

    def testCompile(s: Scanner[Char]): Unit = {
        (fromString("abcd") ~ eps).parse(s, 0, 10)
    }
}
