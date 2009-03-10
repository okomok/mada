

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.iter


private[mada] object ReducerLeft {
    def apply[A, B >: A](it: Iterable[A], op: (B, A) => B): Iterable[B] = Iterables.makeByName(impl(it.elements, op))
    def impl[A, B >: A](it: Iterator[A], op: (B, A) => B): Iterator[B] = FolderLeft.impl[A, B](it, it.next, op)
}
