

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.iter


private[mada] object ToIterable {
    def apply[A](from: => Iterator[A]): Iterable.Projection[A] = new Iterable.Projection[A] {
        override def elements = from
    }
}
