

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.traversable


class Slice[A](val that: Traversable[A], val from: Int, val until: Int) extends Traversable[A] { ^ =>
    override def start = that.drop(from).take(until - from).start
}
