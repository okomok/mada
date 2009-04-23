

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
    final case class Assert[a >: True <: True]()

    /**
     * assertion of type equality
     */
    final case class AssertEquals[a >: b <: b, b]()

    /**
     * assertion if a is lower than b.
     */
    final case class AssertLower[a <: b, b]()

    /**
     * assertion if a is upper than b.
     */
    final case class AssertUpper[a >: b, b]()

}
