

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object Div {
    def apply(a: Int, b: Int): (Int, Int) = {
        (a / b, positiveRemainder(a, b))
    }

    // Don't use heap.
    def positiveRemainder(a: Int, b: Int): Int = {
        Assert(b > 0)
        val rem = a % b
        if (rem < 0) {
            rem + b
        } else {
            rem
        }
    }
}
