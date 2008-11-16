
package mada


class Ref[T](var deref: T) {
    final def :=(x: T): T = { deref = x; deref }
}


class LazyRef[T] {
    private var initialized = false
    private var ref: T = _
    final def :=(x: T): T = synchronized {
        if (initialized) ref else { initialized = true; ref = x; ref }
    }
    final def deref: T = ref
}
