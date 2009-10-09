

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package meta


@specializer
sealed trait Unmeta[From, To] extends scala.Function0[To]


object Unmeta {

    implicit val ofTrue = new Unmeta[`true`, scala.Boolean] {
        override def apply() = true
    }
    implicit val ofFalse = new Unmeta[`false`, scala.Boolean] {
        override def apply() = false
    }

    implicit val ofZero = new Unmeta[Zero, scala.Int] {
        override def apply() = 0
    }
    implicit def ofSucc[n <: Nat](implicit _unmeta: Unmeta[n, scala.Int]) = new Unmeta[Succ[n], scala.Int] {
        override def apply() = 1 + _unmeta()
    }

}
