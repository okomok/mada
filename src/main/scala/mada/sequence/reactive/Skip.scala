

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package sequence; package reactive


import java.util.concurrent.atomic


private class SkipFirst[-T](_1: Function1[T, Unit]) extends Function1[T, Unit] {
    private val begins = new atomic.AtomicBoolean(false)

    override def apply(x: T) = {
        if (!begins.compareAndSet(false, true)) {
            _1(x)
        }
    }
}

private class SkipTimes[-T](_1: T => Unit, _2: Int) extends Function1[T, Unit] {
    private val count = new atomic.AtomicInteger(_2)

    override def apply(x: T): Unit = {
        var old = 0
        do {
            old = count.get
            if (old == 0) {
                return _1(x)
            }
        } while (!count.compareAndSet(old, old - 1))
    }
}


private class SkipWhile[-T](_1: T => Unit, _2: T => Boolean) extends Function1[T, Unit] {
    @volatile private var begins = false

    override def apply(x: T): Unit = {
        if (begins) {
            return _1(x)
        }
        if (!_2(x)) {
            begins = true
            return _1(x)
        }
    }
}
