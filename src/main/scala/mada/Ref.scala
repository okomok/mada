

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


class Ref[A](var deref: A) {
    final def :=(x: A): A = { deref = x; deref }
    final def apply(): A = deref
    final def update(x: A): Unit = deref = x
}


class LazyRef[A] {
    private var initialized = false
    private var ref: A = _
    final def :=(x: A): A = synchronized {
        if (initialized) ref else { initialized = true; ref = x; ref }
    }
    final def deref: A = ref
}
