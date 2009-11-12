

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package sequence; package reactive


import java.util.concurrent.atomic


private class DoFirstTime[A](f: A => Unit) extends Function1[A, Unit] {
    private val flip = new atomic.AtomicBoolean(false)

    override def apply(e: A) = {
        if (flip.compareAndSet(false, true)) {
            f(e)
        }
    }
}

// BUGBUG: onEnd may be called before react...
private class DoTimes[A](n: Int, f: A => Unit, onEnd: Unit => Unit)  extends Function1[A, Unit] {
    private val count = new atomic.AtomicInteger(n)

    override def apply(e: A): Unit = {
        var old = 0
        do {
            old = count.get
            if (old == 0) {
                return onEnd()
            }
        } while (!count.compareAndSet(old, old - 1))

        f(e)
    }
}
