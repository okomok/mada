
package mada


object Assert {
    def apply(msg: Any, cond: => Boolean) {
        if (!NDebug.value && !cond)
            assert(false, msg)
    }
}
