

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


private[mada] object Join {
    def apply[A](v: Vector[A]): Unit = {
        v.foreach({ e => () })
    }
}
