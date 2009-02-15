

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.func


// See: http://citeseer.ist.psu.edu/51062.html

private[mada] object Memoize {
    def apply[T, R](g: Functions.Transform[Function1[T, R]]): Function1[T, R] = Functions.fix(new MemoizeWrap(g).curry)
}

private[mada] class MemoizeWrap[T, R](g: Functions.Transform[Function1[T, R]]) extends Function2[Function1[T, R], T, R] {
    private val memoTable = new scala.collection.jcl.HashMap[T, R]

    override def apply(fixed: Function1[T, R], v: T) = {
        val r = memoTable.get(v)
        if (r.isEmpty) {
            val tmp = g(fixed)(v)
            memoTable.put(v, tmp)
            tmp
        } else {
            r.get
        }
    }
}
