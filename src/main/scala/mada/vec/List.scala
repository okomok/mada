

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


import java.util.Arrays


object ToList {
    def apply[A](v: Vector[A]): List[A] = v.iterator.toList
}
