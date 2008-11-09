
package mada


object Assert {
    def apply(msg: Any, cond: => Boolean) {
        if (!NDebug.value)
            assert(cond, msg)
    }
}

object Verify {
    def apply(msg: Any, cond: Boolean) {
        assert(cond, msg)
    }
}
