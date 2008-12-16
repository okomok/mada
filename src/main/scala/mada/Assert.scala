

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


object Assert {
    // By-name parameters require heap allocation without Yclosure-elim.
    def apply(cond: => Boolean): Unit = {
        // For some reason, optimizer dislikes ! and && operators.
        if (NDebug.value == false) {
            if (!cond) {
                throw new java.lang.AssertionError("assertion failed")
            }
        }
    }

    def apply(msg: => Any, cond: => Boolean): Unit = {
        if (NDebug.value == false) {
            if (!cond) {
                throw new java.lang.AssertionError("assertion failed: " + msg)
            }
        }
    }
}


object Verify {
    def apply(cond: Boolean) = Assert(cond)
    def apply(msg: => Any, cond: Boolean) = Assert(msg, cond)

}


object Check {
    def apply[A](expect: A => Boolean, e: A): A = { Assert(expect(e)); e }
    def apply[A](msg: => Any, expect: A => Boolean, e: A): A = { Assert(msg, expect(e)); e }
}
