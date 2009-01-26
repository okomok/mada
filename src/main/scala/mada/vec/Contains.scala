

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


private[mada] object Contains {
    def apply[A](v: Vector[A], e: Any): Boolean = v.exists(_ == e)
}
