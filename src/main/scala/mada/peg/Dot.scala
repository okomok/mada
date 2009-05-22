

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


case class Dot[A]() extends Forwarder[A] {
    override protected val delegate = advance[A](1)
}
