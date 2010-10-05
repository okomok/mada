

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package sequence; package vector


private
object Div { // doesn't return a tuple to avoid heap-allocation.
    def quotient(a: Int, b: Int): Int = {
        assert(b > 0)
        val q = a / b
        val r = a % b
        if (r < 0) q - 1 else q
    }

    // Returns nonnegative.
    def remainder(a: Int, b: Int): Int = {
        assert(b > 0)
        val r = a % b
        if (r < 0) r + b else r
    }
}
