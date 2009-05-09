

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.blend


// See: Meta-Programming with Scala: Conditional Compilation and Times Unrolling
//      at http://michid.wordpress.com/2008/10/29/meta-programming-with-scala-conditional-compilation-and-loop-unrolling/


import Meta.Nat


@specializer
sealed trait Times[n <: Nat] extends ((Int => Unit, Int) => Unit)


object Times {

    implicit val ofZero = new Times[Nat.zero] {
        override def apply(op: Int => Unit, i: Int) = ()
    }

    implicit def ofSucc[n <: Nat](implicit _times: Times[n]) = new Times[Nat.succ[n]] {
        override def apply(op: Int => Unit, i: Int) = { op(i); _times(op, i + 1) }
    }

}
