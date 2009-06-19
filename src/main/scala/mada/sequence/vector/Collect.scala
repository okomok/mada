

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence.vector


case class Collect[Z, A](_1: Vector[Z], _2: Vector[Z] => A) extends Forwarder[A] {
    override protected val delegate = single(_2(_1))
}

case class ZipCollect[Z1, Z2, A](_1: Vector[Z1], _2: Vector[Z2], _3: (Vector[Z1], Vector[Z2]) => A) extends Forwarder[A] {
    override protected val delegate = single(_3(_1, _2))
}
