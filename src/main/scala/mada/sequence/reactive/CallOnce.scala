

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package sequence; package reactive


// Equivalent to thread-unsafe lazy val.
private class CallOnce[T](f: T => Unit) extends Function1[T, Unit] {
    private var first = true
    override def apply(x: T) = {
        if (first) {
            first = false
            f(x)
        }
    }
}
