

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


@notThreadSafe // fail-fast only
private
class VarOnce[A] {
    @volatile private var x: Option[A] = None
    def value: A = x.get
    def isEmpty: Boolean = x.isEmpty

    def :=(y: A) = {
        if (x.isEmpty) {
            x = Some(y)
        } else {
           throw new IllegalStateException("reassignment is not allowed")
        }
    }
}

private
object VarOnce {
    implicit def _toValue[A](from: VarOnce[A]): A = from.value
}
