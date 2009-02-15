

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.func


import scala.collection.mutable.Map


// See: http://citeseer.ist.psu.edu/51062.html

private[mada] object Memoize {
    def apply[T, R](g: Functions.Transform[Function1[T, R]])(m: Map[T, R]): Function1[T, R] = Functions.fix(new MemoizeWrap(g, m).curry)
}

private[mada] class MemoizeWrap[T, R](g: Functions.Transform[Function1[T, R]], m: Map[T, R]) extends Function2[Function1[T, R], T, R] {
    override def apply(fixed: Function1[T, R], v: T) = {
        val r = m.get(v)
        if (r.isEmpty) {
            val tmp = g(fixed)(v)
            m.put(v, tmp)
            tmp
        } else {
            r.get
        }
    }
}
