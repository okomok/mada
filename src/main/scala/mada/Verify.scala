
package mada


object Verify {
    def apply(msg: Any, cond: Boolean) {
        assert(cond, msg)
    }
}
