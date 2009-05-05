

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.meta


/**
 * Contains meta assertions.
 */
@provider
trait Asserts { this: Meta.type =>

    // Prefer methods to case classes:
    //   case classes permit should-be-illegal expression.

    /**
     * assertion
     */
    def assert[a >: `true` <: `true`]: scala.Unit = ()

    /**
     * assertion of identity equality
     */
    def assertSame[a >: b <: b, b]: scala.Unit = ()

    /**
     * assertion if <code>a</code> is lower than <code>b</code>.
     */
    def assertLower[a <: b, b]: scala.Unit = ()

    /**
     * assertion if <code>a</code> is upper than <code>b</code>.
     */
    def assertUpper[a >: b, b]: scala.Unit = ()

}
