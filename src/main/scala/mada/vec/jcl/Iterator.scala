

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec.jcl


import java.util.Iterator


object FromIterator {
    def apply[A](u: Iterator[A]): Vector[A] = {
        val a = new java.util.ArrayList[A]
        while (u.hasNext) {
            a.add(u.next)
        }
        Vector.jclListVector(a)
    }
}
