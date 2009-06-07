

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence.iterative


case class FolderLeft[A, B](_1: Iterative[A], _2: B, _3: (B, A) => B) extends Forwarder[B] {
    override protected val delegate = single(_2) ++ new _FolderLeft(_1, _2, _3)
}

private class _FolderLeft[A, B](_1: Iterative[A], _2: B, _3: (B, A) => B) extends Iterative[B] {
    override def begin = new Iterator[B] {
        private val it = _1.begin
        private var z = _2

        override def isEnd = !it
        override def deref = _3(z, ~it)
        override def increment = {
            z = deref
            it.++
        }
    }
}


case class ReducerLeft[A, B >: A](_1: Iterative[A], _2: (B, A) => B) extends Iterative[B] {
    override def begin = {
        Precondition.notEmpty(_1, "reduceLeft")
        val it = _1.begin // needs a fresh iterator every time.
        val e = ~it
        it.++
        bind(it).folderLeft[B](e)(_2).begin
    }
}
