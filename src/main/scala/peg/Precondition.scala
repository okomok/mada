

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package peg


private
object Precondition {

    def zeroWidth[A](p: Peg[A], method: String): Unit = {
        if (IsZeroWidth(p)) {
            throw new IllegalArgumentException(method + " doesn't allow zero-width.")
        }
    }

}
