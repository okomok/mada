

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence.vector


case class Folder[A](_1: Vector[A], _2: A, _3: (A, A) => A) extends Forwarder[A] {
    override protected val delegate = _1.asIterative.folderLeft(_2)(_3).toVector
}

case class Reducer[A](_1: Vector[A], _2: (A, A) => A) extends Forwarder[A] {
    Precondition.notEmpty(_1, "reducer")
    override protected val delegate = _1.asIterative.reducerLeft(_2).toVector
}
