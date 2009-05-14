

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.traversable


case class FolderLeft[A, B](_1: Traversable[A], _2: B, _3: (B, A) => B) extends Traversable[B] {
    override def start = (single(_2) ++ new _FolderLeft(_1, _2, _3)).start
}

private class _FolderLeft[A, B](_1: Traversable[A], _2: B, _3: (B, A) => B) extends Traversable[B] {
    override def start = new Traverser[B] {
        private val t = _1.start
        private var z = _2
        override def isEnd = !t
        override def deref = _3(z, ~t)
        override def increment = {
            z = deref
            t.++
        }
    }
}


case class ReducerLeft[A, B >: A](_1: Traversable[A], _2: (B, A) => B) extends Forwarder[B] {
    override val delegate = {
        val t = _1.start
        if (!t) {
            throw new UnsupportedOperationException("reducerLeft on empty traversable")
        }
        val e = ~t
        t.++
        bind(t).folderLeft[B](e)(_2)
    }
}
