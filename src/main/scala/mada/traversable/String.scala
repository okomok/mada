

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.traversable


case class Unstringize(_1: String) extends Forwarder[Char] {
    override protected val delegate = fromIterable(_1)
}
