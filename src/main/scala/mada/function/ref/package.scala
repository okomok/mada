

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package function


/**
 * Contains utility methods operating on <code>Function</code> and references.
 */
package object ref {

    @equivalentTo("{ (v1, v2) => v1 eq v2 }")
    val equal: Predicate2[AnyRef, AnyRef] = { (v1, v2) => v1 eq v2 }

    @equivalentTo("{ v2 => v1 eq v2 }")
    def equalTo(v1: AnyRef): Predicate1[AnyRef] = { v2 => v1 eq v2 }

}
