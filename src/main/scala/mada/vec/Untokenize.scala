

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


private[mada] object Untokenize {
    def apply[A](vs: Iterator[Vector[A]], sep: Vector[A]): Vector[A] = {
        Vector.flatten(vs.map({ v => sep.append(v) }))
    }
}
