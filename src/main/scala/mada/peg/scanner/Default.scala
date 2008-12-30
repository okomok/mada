

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg.scanner


object Default {
    def apply[A](v: Vector[A]) = new Default(v)
}

class Default[A](v: Vector[A]) extends Scanner[A] with Vector.VectorProxy[A] {
    override def self = v

    override def copy[B](v: Vector[B]) = new Default(v)
    override def isActionable = actionable
    override def setActionable(b: Boolean) = actionable = b

    private var actionable = true
}
