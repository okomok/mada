

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.iter


private[mada] object Flatten {
    def apply[A](its: Iterable[Iterable[A]]): Iterable[A] = Iterables.byName(iimpl(its.elements.map{ it => it.elements }))
    def iimpl[A](its: Iterator[Iterator[A]]): Iterator[A] = Iterator.flatten(its)
}
