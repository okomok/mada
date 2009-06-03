

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence.vector


private[mada] case class FromOption[A](from: Option[A]) extends Vector[A] {
    override def start = 0
    override def end = if (from.isEmpty) 0 else 1
    override def apply(i: Int) = from.get
}

private[mada] object FirstOption {
    def apply[A](v: Vector[A]): Option[A] = v match {
        case FromOption(_from) => _from // conversion fusion
        case _ => if (v.isEmpty) None else Some(v.head)
    }
}

private[mada] object LastOption {
    def apply[A](v: Vector[A]): Option[A] = v match {
        case FromOption(_from) => _from // conversion fusion
        case _ => if (v.isEmpty) None else Some(v.last)
    }
}
