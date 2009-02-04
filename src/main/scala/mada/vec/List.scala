

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


import java.util.Arrays


private[mada] object ToList {
    def apply[A](from: Vector[A]): List[A] = from.toIterator.toList
}
