

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.iter


private[mada] object Flatten {
    def apply[A](its: Iterable[Iterable[A]]): Iterable[A] = Iterables.makeByName(iimpl(its.projection.map{ it => it.elements }.elements))
    def iimpl[A](its: Iterator[Iterator[A]]): Iterator[A] = Iterator.flatten(its)
}
