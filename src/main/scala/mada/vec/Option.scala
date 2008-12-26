

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object OptionVector {
    def apply[A](u: Option[A]): Vector[A] = new OptionVector(u)
}

class OptionVector[A](val option: Option[A]) extends Vector[A] {
    override def size = if (option.isEmpty) 0 else 1
    override def apply(i: Long) = option.get

    override def toOption = option // from-to fusion
}


object ToOption {
    def apply[A](v: Vector[A]): Option[A] = {
        if (v.isEmpty) {
            None
        } else {
            Some(v.first)
        }
    }
}
