

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package sequence; package reactive


@notThreadSafe
private class OneTimeVar[A] {
    private var x: Option[A] = None
    def value: A = x.get
    def :=(y: A) = {
        if (x.isEmpty) {
            x = Some(y)
        } else {
           throw new IllegalStateException("reassignment is not allowed")
        }
    }
}

private object OneTimeVar {
    implicit def _toValue[A](from: OneTimeVar[A]): A = from.value
}
