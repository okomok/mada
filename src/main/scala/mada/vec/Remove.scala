

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


private[mada] object Remove {
    def apply[A](v: Vector[A], p: A => Boolean): Vector[A] = v.filter(Functions.not(p))
}

private[mada] object MutatingRemove {
    def apply[A](v: Vector[A], p: A => Boolean): Vector[A] = v.mutatingFilter(Functions.not(p))
}
