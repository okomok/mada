

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


private[mada] object OrderedViewWith {
    def apply[A](lt: Functions.Compare[A]): Functions.OrderedView[Vector[A]] = {
        val vlt = { (v: Vector[A], w: Vector[A]) => Stl.lexicographicalCompare(v, v.start, v.end, w, w.start, w.end, lt) }
        Less.toOrderedView(vlt)
    }
}
