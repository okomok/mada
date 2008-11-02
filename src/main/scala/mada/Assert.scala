
package mada


object Assert {
    def apply(msg: Any, cond: => Boolean) {
        if (!NDebug.value)
            assert(cond, msg)
    }
}
