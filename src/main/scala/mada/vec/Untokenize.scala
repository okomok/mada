

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object Untokenize {
    def apply[Z, A](vv: Vector[Vector[A]], sep: Vector[A]): Vector[A] = Vector.flatten(vv.map(sep.append(_: Vector[A])))
}
