

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence.iterative


case class Using[+A](_1: Iterative[A], _2: Seq[Auto[_]]) extends Auto[Iterative[A]] {
    override def get = _1
    override def begin = _2.foreach(_.begin)
    override def end = _2.reverse.foreach(_.end)
}
