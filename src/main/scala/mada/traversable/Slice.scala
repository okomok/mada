

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.traversable


class Slice[A](val _1: Traversable[A], val _2: Int, val _3: Int) extends Traversable[A] { self =>
    override def start = _1.drop(_2).take(_3 - _2).start
}
