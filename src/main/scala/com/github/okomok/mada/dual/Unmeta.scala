

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package dual


@specializer
trait Unmeta[From, To] extends scala.Function0[To]


object Unmeta {

    implicit val _ofTrue = new Unmeta[`true`, scala.Boolean] {
        override def apply() = true
    }
    implicit val _ofFalse = new Unmeta[`false`, scala.Boolean] {
        override def apply() = false
    }

    implicit val _ofZero = new Unmeta[Zero, scala.Int] {
        override def apply() = 0
    }
    implicit def _ofSucc[n <: Nat](implicit _unmeta: Unmeta[n, scala.Int]) = new Unmeta[Succ[n], scala.Int] {
        override def apply() = 1 + _unmeta()
    }

}
