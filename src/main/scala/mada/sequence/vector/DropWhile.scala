

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence.vector


case class DropWhile[A](_1: Vector[A], _2: A => Boolean) extends Forwarder[A] {
    override protected val delegate = _1(stl.FindIf(_1, _1.start, _1.end, function.not(_2)), _1.end)
}