

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object Div {
    def apply(a: Long, b: Long): (Long, Long) = {
        (a / b, positiveRemainder(a, b))
    }

    // Don't use heap.
    def positiveRemainder(a: Long, b: Long): Long = {
        Assert(b > 0)
        val rem = a % b
        if (rem < 0) {
            rem + b
        } else {
            rem
        }
    }
}
