

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package metatest


import com.github.okomok.mada

import mada.meta._
// import junit.framework.Assert._


object AlwaysTezt {

    trait Strong
    trait Strung
    final class so extends Strong
    final class su extends Strung

    trait testTrivial {
        type k = always[su]#apply1[so]
        assertSame[su, k]
        assertSame[su, always[su]#apply0]
        assertSame[su, always[su]#apply0]
        assertSame[su, always[su]#apply1[so]]
        assertSame[su, always[su]#apply2[scala.Int, so]]
        assertSame[su, always[su]#apply3[scala.Int, so, so]]
    }
}
