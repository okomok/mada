

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.traversable


case class FolderLeft[A, B](_1: Traversable[A], _2: B, _3: (B, A) => B) extends Traversable[B] {
    override def begin = (single(_2) ++ new _FolderLeft(_1, _2, _3)).begin
}

private class _FolderLeft[A, B](_1: Traversable[A], _2: B, _3: (B, A) => B) extends Traversable[B] {
    override def begin = new Traverser[B] {
        private val t = _1.begin
        private var z = _2

        override def isEnd = !t
        override def deref = _3(z, ~t)
        override def increment = {
            z = deref
            t.++
        }
    }
}


case class ReducerLeft[A, B >: A](_1: Traversable[A], _2: (B, A) => B) extends Traversable[B] {
    override def begin = {
        val t = _1.begin
        if (!t) {
            throw new UnsupportedOperationException("reducerLeft on empty traversable")
        }
        val e = ~t
        t.++
        bind(t).folderLeft[B](e)(_2).begin
    }
/*
    override protected val delegate = {
        val t = _1.begin
        if (!t) {
            throw new UnsupportedOperationException("reducerLeft on empty traversable")
        }
        val e = ~t // too early?, but lazyness can't always be feasible. (BTW, Vector is usually infeasible.)
        t.++
        bind(t).folderLeft[B](e)(_2)
    }
*/
}
