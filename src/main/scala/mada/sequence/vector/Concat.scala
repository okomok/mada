

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence.vector


private[mada] object Concat {
    def apply[A](vs: Vector[A]*): Vector[A] = {
        vs.foldLeft(vector.empty[A]){ (r, v) => r ++ v }
    }
}
