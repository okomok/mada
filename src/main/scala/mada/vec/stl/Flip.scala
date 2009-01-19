

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec.stl


object Flip {
    def apply[A, B, C](f: (A, B) => C): (B, A) => C = { (b: B, a: A) => f(a, b) }
}
