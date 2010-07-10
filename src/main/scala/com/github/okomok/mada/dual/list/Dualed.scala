

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package list


/*
@specializer
sealed abstract class Dualed[xs <: List] extends (sequence.List[scala.Any] => xs)


object Dualed {

    implicit val _ofNil = new Dualed[Nil] {
        override def apply(xs: sequence.List[scala.Any]) = Nil
    }

    implicit def _ofCons[a, as <: List](implicit _dualed: Dualed[as]) = new Dualed[Cons[a, as]] {
        override def apply(xs: sequence.List[scala.Any]) = Cons(Box(xs.head.asInstanceOf[a]), _dualed(xs.tail))
    }

}
*/
