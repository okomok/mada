

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


@notThreadSafe
private[reactive]
class OneTimeVar[A] {
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

private[reactive]
object OneTimeVar {
    implicit def _toValue[A](from: OneTimeVar[A]): A = from.value
}
