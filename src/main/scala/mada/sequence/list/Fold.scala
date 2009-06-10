

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence.list


case class FolderLeft[A, B](_1: List[A], _2: B, _3: (B, A) => B) extends Forwarder[B] {
    override protected val delegate = {
        cons(
            _2,
            if (_1.isNil) {
                nil
            } else {
                _1.tail.folderLeft(_3(_2, _1.head))(_3)
            }
        )
    }
}

case class ReducerLeft[A, B >: A](_1: List[A], _2: (B, A) => B) extends Forwarder[B] {
    Precondition.notEmpty(_1, "reducerLeft")
    override protected val delegate = _1.tail.folderLeft[B](_1.head)(_2)
}
