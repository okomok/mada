

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec.parallel


class Breakable1[A](p: A => Boolean, ret: Boolean) extends (A => Boolean) {
    private val isBreak = new java.util.concurrent.atomic.AtomicBoolean(false)
    override def apply(a: A) = if (isBreak.get) ret else p(a)
    final def break = isBreak.set(true)
}

class Breakable2[A, B](p: (A, B) => Boolean, ret: Boolean) extends ((A, B) => Boolean) {
    private val isBreak = new java.util.concurrent.atomic.AtomicBoolean(false)
    override def apply(a: A, b: B) = if (isBreak.get) ret else p(a, b)
    final def break = isBreak.set(true)
}
