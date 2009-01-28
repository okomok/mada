

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


private[mada] object Forall {
    def apply[A](v: Vector[A], p: A => Boolean): Boolean = v.seek(Functions.not(p)).isEmpty
}
