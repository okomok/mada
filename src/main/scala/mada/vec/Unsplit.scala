

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


private[mada] object Unsplit {
    def apply[A](vs: Iterable[Vector[A]], sep: Vector[A]): Vector[A] = {
        Vector.flatten(vs.projection.map{ v => sep.append(v) })
    }
}