

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


private[mada] object Pareach {
    def apply[A](v: Vector[A], f: A => Unit): Unit = v.foreach(f)
}
