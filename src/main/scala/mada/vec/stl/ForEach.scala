

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec.stl


object ForEach {
    def apply[A, F <: (A => Any)](v: Vector[A], _2: F): F = {
        v.loop({ (e: A) => _2(e); true })
        _2
    }
}
