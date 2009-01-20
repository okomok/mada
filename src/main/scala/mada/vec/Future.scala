

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object Future {
    def apply[Z, A](v: Vector[Z], f: Z => A): Vector[A] = {
        v.map({ e => scala.actors.Futures.future(f(e)) }).touch.map({ u => u()})
    }
}
