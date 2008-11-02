
package mada


object Assert {
    private var enabled: Boolean = true

    final def enable {
        enabled = true
    }

    final def disable {
        enabled = false
    }

    final def apply(cond: => Boolean) {
        if (enabled && !cond)
            assert(false)
    }
}
