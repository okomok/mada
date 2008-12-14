

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.rng.detail


import Pointer._


object PointerSwap {
    def apply[A](p: Pointer[A], q: Pointer[A]) = {
        val tmp = *(p)
        *(p) = *(q)
        *(q) = tmp
    }

    def apply[A](* : Pointer[A], i: Long, j: Long) = {
        val tmp = *(i)
        *(i) = *(j)
        *(j) = tmp
    }
}
