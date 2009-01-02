

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


class Ref[A](var deref: A) {
    final def :=(x: A): Unit = deref = x
    final def apply(): A = deref
    final def update(x: A): Unit = deref = x
}


class LazyRef[A] {
    private var initialized = false
    private var ref: A = _
    final def :=(x: A): Unit = synchronized {
        if (!initialized) { initialized = true; ref = x }
    }
    final def deref: A = ref
}
