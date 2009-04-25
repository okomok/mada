

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.meta


/**
 * Contains meta assertions.
 */
trait Asserts { this: Meta.type =>

    /**
     * assertion
     */
    def assert[a >: `true` <: `true`]: Unit = ()

    /**
     * assertion of identity equality
     */
    def assertSame[a >: b <: b, b]: Unit = ()

    /**
     * assertion if a is lower than b.
     */
    def assertLower[a <: b, b]: Unit = ()

    /**
     * assertion if a is upper than b.
     */
    def assertUpper[a >: b, b]: Unit = ()

}
