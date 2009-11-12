

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package function


import java.util.concurrent.atomic


case class Skip[-T](_1: Int, _2: T => Unit) extends Function1[T, Unit] {
    private val count = new atomic.AtomicInteger(_1)

    override def apply(x: T): Unit = {
        var old = 0
        do {
            old = count.get
            if (old == 0) {
                return _2(x)
            }
        } while (!count.compareAndSet(old, old - 1))
    }
}


case class SkipFirst[-T](_1: Function1[T, Unit]) extends Function1[T, Unit] {
    private val flip = new atomic.AtomicBoolean(false)

    override def apply(x: T) = {
        if (!flip.compareAndSet(false, true)) {
            _1(x)
        }
    }
}
