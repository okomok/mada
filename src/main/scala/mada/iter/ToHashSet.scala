

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.iter


private[mada] object ToHashSet {
    def apply[A](from: Iterator[A]): scala.collection.Set[A] = {
        val to = new scala.collection.jcl.HashSet[A]
        for (e <- from) {
            to.add(e)
        }
        to
    }
}
