

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package auto


case class Filter[A](_1: Auto[A], _2: A => Boolean) extends Auto[A] {
    private lazy val ok = _2(_1.get)
    override def get = _1.get
    override def begin = if (ok) _1.begin
    override def end = if (ok) _1.end
}
