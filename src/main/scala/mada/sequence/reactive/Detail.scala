

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package sequence; package reactive


private class SkipFirstTime[A](f: A => Unit) {
    private val eitherEnds = new java.util.concurrent.atomic.AtomicBoolean(false)

    def apply(e: A): Unit = {
        if (!eitherEnds.compareAndSet(false, true)) {
            f(e)
        }
    }
}
