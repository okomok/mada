

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.hetero


import mada.Meta._
// import junit.framework.Assert._


class QuoteTest {

    // "types"
    trait Strong extends Object
    trait Strung extends Object

    // "objects"
    final class so extends Strong
    final class su extends Strung

    // "methods"
    type get1[a <: Strong, b <: Strung] = a
    type get2[a <: Strong, b <: Strung] = b

    // "functions"
    type q1 = quote2[Strong, Strung, Strong, get1]
    type q2 = quote2[Strong, Strung, Strung, get2]

    def testTrivial: Unit = {
        assertEquals[quote1[string, string, identity]#apply[string], string]
        assertEquals[q1#apply[so, su], so]
        assertEquals[q2#apply[so, su], su]
    }
}
