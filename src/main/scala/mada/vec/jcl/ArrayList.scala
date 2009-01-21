

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec.jcl


import java.util.ArrayList


object ToArrayList {
    def apply[A](v: Vector[A]): ArrayList[A] = {
        val a = new ArrayList[A](v.size) // this is capacity.
        for (e <- v) {
            a.add(e)
        }
        a
    }
}


object NewArrayList {
    def apply[A](es: A*): ArrayList[A] = {
        val a = new ArrayList[A](es.length)
        for (e <- es) {
            a.add(e)
        }
        a
    }
}
