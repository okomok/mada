

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package sequence; package vector


// Ordering should have taken [-A]?
private[mada] case class LexicographicalOrdering[A](_1: Ordering[A]) extends Ordering[Vector[A]] {
    override def compare(v: Vector[A], w: Vector[A]) = stl.LexicographicalCompare3way(v, v.start, v.end, w, w.start, w.end, _1)
}
