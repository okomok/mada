

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


class Call[A](body: => Unit) extends Forwarder[A] {
    val _1 = function.ofName(body)

    override protected val delegate = eps[A] act { _ => _1() }
}
