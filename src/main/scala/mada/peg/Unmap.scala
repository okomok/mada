

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


object Unmap {
    def apply[Z, A](p: Peg[A], f: Z => A): Peg[Z] = p.prescan((_: Vector[Z]).map(f))
}
