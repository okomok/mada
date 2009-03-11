

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.iter


private[mada] object StringFrom {
    def apply[A](from: Iterable[A]): String = {
        val a = new java.util.ArrayList[A]
        for (e <- from.projection) {
            a.add(e)
        }
        a.toString
    }
}
