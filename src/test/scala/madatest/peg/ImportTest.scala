

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.pegtest


import junit.framework.Assert._


import mada.peg.compatibles._ // "strong" import (not type-sensitive but name-sensitive)

class ImportTest {
    def testNotAmbiguous = {
        "abcde" >> "abcde" // needs strong import to look up `>>`.

        usePeg("abcde") // not ambiguous even with strong import
    }

    def usePeg[A](p: mada.Peg[A]) = ()
}
