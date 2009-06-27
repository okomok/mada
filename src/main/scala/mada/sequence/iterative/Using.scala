

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence.iterative


case class Using[+A](_1: Iterative[A], _2: Seq[Auto[_]]) extends Auto[Iterative[A]] {
    override def autoRef = _1
    override def autoBegin = _2.foreach(_.autoBegin)
    override def autoEnd = _2.reverse.foreach(_.autoEnd)
}
