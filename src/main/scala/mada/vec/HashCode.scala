

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


private[mada] object HashCode_ {
    def apply[A](v: Vector[A]): Int = {
        val n = v.size
        val nh = HashCode.ofInt(n)

        if (n == 0) {
            nh
        } else if (n == 1) {
            nh + v.first.hashCode * 7
        } else if (n == 2) {
            nh + v.first.hashCode * 7 + v.last.hashCode * 41
        } else {
            nh + v.first.hashCode * 7 + v.last.hashCode * 41 + v(n/2).hashCode * 53
        }
    }
}
