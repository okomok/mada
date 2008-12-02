
package mada


class Ref[A](var deref: A) {
    final def :=(x: A): A = { deref = x; deref }
}


class LazyRef[A] {
    private var initialized = false
    private var ref: A = _
    final def :=(x: A): A = synchronized {
        if (initialized) ref else { initialized = true; ref = x; ref }
    }
    final def deref: A = ref
}
