

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


trait Forwarder[A] extends Peg[A] with mada.any.Forwarder {
    override protected def delegate: Peg[A]

    protected def beforeForward[B](that: Peg[B]): Peg[B] = that

    override def parse(v: Vector[A], start: Int, end: Int) = beforeForward(delegate).parse(v, start, end)
    override def width = beforeForward(delegate).width

    override def toString: String = beforeForward(delegate).toString
}
