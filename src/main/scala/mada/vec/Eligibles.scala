

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


/**
 * Contains eligibles around <code>Vector</code>.
 */
trait Eligibles {

    // Hmm, Ordering should have taken [-A]?
    implicit def forOrdering[A](implicit e: Ordering[A]): Ordering[Vector[A]] = new Ordering[Vector[A]] {
        private val _lt = Compare.fromOrdering(e)
        override def compare(v: Vector[A], w: Vector[A]) = {
            Stl.lexicographicalCompare3way(v, v.start, v.end, w, w.start, w.end, _lt)
        }
    }

/* falls into ambiguity.
    implicit def forOrdering_[A](implicit e: Compare.GetOrdered[A]): Ordering[Vector[A]] = new Ordering[Vector[A]] {
        private val _lt = Compare.fromGetOrdered(e)
        override def compare(v: Vector[A], w: Vector[A]) = {
            Stl.lexicographicalCompare3way(v, v.start, v.end, w, w.start, w.end, _lt)
        }
    }
*/
}
