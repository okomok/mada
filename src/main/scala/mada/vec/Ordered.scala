

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


private[mada] object ToOrdered {
    def apply[A](from: Vector[A], c: Compare.GetOrdered[A]): Ordered[Vector[A]] = Vector.orderedView(c)(from)
}
