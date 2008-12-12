

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.rng.detail


import Pointer._


object PointerOutput {
    def apply[A](p: Pointer[A], e: A): Pointer[A] = {
        *(p) = e
        ++(p)
    }
}
