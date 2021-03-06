

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package function


/**
 * Contains utility methods operating on <code>Function</code> and typed references.
 */
package object typed {

    @annotation.equivalentTo("{ (v1, v2) => v1 == v2 }")
    def equal[T1, T2]: Predicate2[T1, T2] = function.equal.asInstanceOf[Predicate2[T1, T2]]

}
