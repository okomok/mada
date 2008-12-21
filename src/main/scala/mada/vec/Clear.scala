

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object Clear {
    def apply[A](v: Vector[A]) = v.window(0, 0)
}
