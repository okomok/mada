

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package blend


// See: meta.Programming with Scala: Conditional Compilation and Times Unrolling
//      at http://michid.wordpress.com/2008/10/29/meta-programming-with-scala-conditional-compilation-and-loop-unrolling/


@specializer
sealed trait Times[n <: meta.Nat] extends ((Int => Unit, Int) => Unit)


object Times {

    implicit val ofZero = new Times[meta.Zero] {
        override def apply(op: Int => Unit, i: Int) = ()
    }

    implicit def ofSucc[n <: meta.Nat](implicit _times: Times[n]) = new Times[meta.Succ[n]] {
        override def apply(op: Int => Unit, i: Int) = { op(i); _times(op, i + 1) }
    }

}
