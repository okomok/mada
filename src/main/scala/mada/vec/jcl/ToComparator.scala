

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec.jcl


import java.util.Comparator


private[mada] object ToComparator {
    def apply[A](lt: Functions.Compare[A]): Comparator[A] = new Comparator[A] {
        override def compare(x: A, y: A): Int = {
            if (lt(x, y)) {
                -1
            } else if (lt(y, x)) {
                1
            } else {
                0
            }
        }
    }
}
