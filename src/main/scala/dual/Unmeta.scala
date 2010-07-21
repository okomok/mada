

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual


@specializer
trait Unmeta[x <: Any] extends scala.Function0[x]


object Unmeta {


// Boolean

    implicit val _ofTrue = new Unmeta[`true`] {
        override def apply: `true` = `true`
    }
    implicit val _ofFalse = new Unmeta[`false`] {
        override def apply: `false` = `false`
    }


// nat.Peano

    implicit val _ofNatPeanoZero = new Unmeta[nat.peano.Zero] {
        override def apply: nat.peano.Zero = nat.peano.Zero
    }
    implicit def _ofNatPeanoSucc[n <: nat.peano.Peano](implicit _unmeta: Unmeta[n]) = new Unmeta[nat.peano.Succ[n]] {
        override def apply: nat.peano.Succ[n] = nat.peano.Succ(_unmeta.apply)
    }


// Unit

    implicit val _ofUnit = new Unmeta[Unit] {
        override def apply: Unit = Unit
    }

}
