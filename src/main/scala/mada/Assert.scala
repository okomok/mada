

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


object Assert {
    def apply(msg: => Any, cond: => Boolean): Unit = {
        if (!NDebug.value && !cond) {
            throw new java.lang.AssertionError("assertion failed: " + msg)
        }
    }
}

object Verify {
    def apply(msg: => Any, cond: Boolean) = Assert(msg, cond)
}
