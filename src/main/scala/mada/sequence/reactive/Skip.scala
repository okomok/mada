

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package sequence; package reactive


import java.util.concurrent.atomic._


private class SkipFirstTime[A](f: A => Unit) {
    private val flip = new AtomicBoolean(false)

    def apply(e: A): Unit = {
        if (!flip.compareAndSet(false, true)) {
            f(e)
        }
    }
}


private class SkipTimes[A](n: Int, f: A => Unit) {
    private val count = new AtomicInteger(n)

    def apply(e: A): Unit = {
        var old = 0
        do {
            old = count.get
            if (old == 0) {
                return f(e)
            }
        } while (!count.compareAndSet(old, old - 1))
    }
}
