

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.iter


private[mada] object By {
    def apply[A](it: Iterator[A]): Iterable[A] = new Iterable.Projection[A] {
        override val elements = it
    }
}

private[mada] object ByName {
    def apply[A](it: => Iterator[A]): Iterable[A] = new Iterable.Projection[A] {
        override def elements = it
    }
}