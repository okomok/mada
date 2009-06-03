

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence.vector


private[mada] object Loop {
    def apply[A, F <: (A => Boolean)](* : Vector[A], first: Int, __last: Int, __f: F): F = {
        var __first = first

        while (__first != __last && __f(*(__first))) {
            __first += 1
        }
        __f
    }
}
