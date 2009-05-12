

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.traversable


case class Slice[A](that: Traversable[A], from: Int, until: Int) extends Traversable[A] { ^ =>
    override def start = that.drop(from).take(until - from).start
}
