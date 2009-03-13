

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.cmp


private[mada] object Compare3way {
    def apply[A](x: A, lt: Compare.Type[A], y: A): Int = {
        if (lt(x, y)) -1 else if (lt(y, x)) 1 else 0
    }
}
