

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.iter



private[mada] object Cycle {
    def apply[A](it: Iterable[A]): Iterator[A] = {
        Iterators.repeat(()).flatMap{ (u: Unit) => it.elements }
    }
}

/*
private[mada] object Cycle {
    def apply[A](it: Iterator[A]): Iterator[A] = {
        val buf = new java.util.ArrayList[A]
        var firstTime = true
        val f = { (u: Unit) =>
            if (firstTime) {
                firstTime = false
                Iterators.withSideEffect(it)({ e => buf.add(e) })
            } else {
                Iterators.fromJclIterator(buf.iterator)
            }
        }
        Iterators.repeat(()).flatMap(f)
    }
}
*/
