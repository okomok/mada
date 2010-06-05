

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package metatest


import com.github.okomok.mada

import mada.meta._
// import junit.framework.Assert._


object QuoteTezt {

    // "types"
    trait Strong
    trait Strung

    // "objects"
    final class so extends Strong
    final class su extends Strung

    // "methods"
    type get1[a <: Strong, b <: Strung] = a
    type get2[a <: Strong, b <: Strung] = b

    // "functions"
    type q1 = quote2[get1, Strong, Strung]
    type q2 = quote2[get2, Strong, Strung]

    trait testTrivial {
        assertSame[quote1[identity, String]#apply[String], String]
        assertSame[q1#apply[so, su], so]
        assertSame[q2#apply[so, su], su]
    }
}
