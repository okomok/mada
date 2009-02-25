

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


private[mada] object Unzip {
    def apply[A, B](v: Vector[(A, B)]): (Vector[A], Vector[B]) = {
        (v.map{ ab => ab._1 }, v.map{ ab => ab._2 })
    }
}
