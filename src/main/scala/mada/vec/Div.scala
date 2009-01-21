

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


// Java guarantees positive remainder?

object Div { // doesn't return a tuple to need heap-allocation.
    def quotient(a: Int, b: Int): Int = {
        Assert(b > 0)
        val q = a / b
        val r = a % b
        if (r < 0) q - 1 else q
    }

    def remainder(a: Int, b: Int): Int = {
        Assert(b > 0)
        val r = a % b
        if (r < 0) r + b else r
    }
}
