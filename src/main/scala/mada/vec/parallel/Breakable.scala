

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec.parallel


import java.util.concurrent.atomic.AtomicBoolean


private[mada] class Breakable1[A](p: A => Boolean, ret: Boolean) extends (A => Boolean) {
    private val breaker = new AtomicBoolean(false)
    override def apply(a: A) = if (breaker.get) ret else p(a)
    final def break = breaker.set(true)
}

private[mada] class Breakable2[A, B](p: (A, B) => Boolean, ret: Boolean) extends ((A, B) => Boolean) {
    private val breaker = new AtomicBoolean(false)
    override def apply(a: A, b: B) = if (breaker.get) ret else p(a, b)
    final def break = breaker.set(true)
}
