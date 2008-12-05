
package mada


object Assert {
    def apply(msg: => Any, cond: => Boolean) {
        if (!NDebug.value)
            assert(cond, msg) // BUGBUG: msg is always evaluated.
    }
}

object Verify {
    def apply(msg: => Any, cond: Boolean) {
        assert(cond, msg)
    }
}
