

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.meta


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
    type q1 = quote2[get1, Strong, Strung, Strong]
    type q2 = quote2[get2, Strong, Strung, Strung]

    def testTrivial: Unit = {
        assertSame[quote1[identity, box[String], box[String]]#apply1[box[String]], box[String]]
        assertSame[q1#apply2[so, su], so]
        assertSame[q2#apply2[so, su], su]
    }
}
