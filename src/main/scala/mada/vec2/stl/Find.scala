

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec2.stl


object FindIf {
    def apply[A](v: Vector[A], p: A => Boolean): Long = {
        var i = 0
        v.loop({ (e: A) => if (p(e)) { false } else { i += 1; true } })
        i
    }
}
