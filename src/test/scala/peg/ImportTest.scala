

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package pegtest


import com.github.okomok.mada

import junit.framework.Assert._


import mada.peg.Compatibles._ // "strong" import (not type-sensitive but name-sensitive)

class ImportTest extends org.scalatest.junit.JUnit3Suite {
    def testNotAmbiguous: Unit = {
        "abcde" >> "abcde" // needs strong import to look up `>>`.

        usePeg("abcde") // not ambiguous even with strong import
    }

    def usePeg[A](p: mada.Peg[A]) = ()
}
