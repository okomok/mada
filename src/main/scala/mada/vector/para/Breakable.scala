

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vector.para


private[mada] class Breakable1[A](p: A => Boolean, ret: Boolean) extends (A => Boolean) {
    @volatile private var breaks = false
    override def apply(a: A) = if (breaks) ret else p(a)
    final def break: Unit = breaks = true
}

private[mada] class Breakable2[A, B](p: (A, B) => Boolean, ret: Boolean) extends ((A, B) => Boolean) {
    @volatile private var breaks = false
    override def apply(a: A, b: B) = if (breaks) ret else p(a, b)
    final def break: Unit = breaks = true
}
