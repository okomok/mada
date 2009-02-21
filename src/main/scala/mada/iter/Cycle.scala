

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.iter


private[mada] object Cycle {
    def apply[A](it: Iterable[A]): Iterable.Projection[A] = {
        Iterators.toIterable(Iterators.repeat(()).flatMap{ (u: Unit) => it.elements })
    }
}
