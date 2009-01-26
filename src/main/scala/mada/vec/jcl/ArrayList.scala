

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec.jcl


import java.util.ArrayList


private[mada] object ToArrayList {
    def apply[A](from: Vector[A]): ArrayList[A] = {
        val a = new ArrayList[A](from.size) // this is capacity.
        for (e <- from) {
            a.add(e)
        }
        a
    }
}
