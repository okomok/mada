

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec.jcl


import java.util.Iterator


private[mada] object FromIterator {
    def apply[A](from: Iterator[A]): Vector[A] = {
        val a = new java.util.ArrayList[A]
        while (from.hasNext) {
            a.add(from.next)
        }
        Vector.fromJclList(a)
    }
}
