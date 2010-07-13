

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package list


private[mada] final class Unzip {
     def apply[xs <: List](xs: xs): apply[xs] = `if`(xs.isEmpty, always0(Tuple2(Nil, Nil)), Else(xs)).apply.asInstanceOfProduct2
    type apply[xs <: List] = `if`[xs#isEmpty, always0[Tuple2[Nil, Nil]], Else[xs]]#apply#asInstanceOfProduct2

    final case class Else[xs <: List](xs: xs) extends Function0 {
        override  val self = this
        override type self = Else[xs]
        private lazy val x: x = xs.head.asInstanceOfProduct2
        private type x = xs#head#asInstanceOfProduct2
        private lazy val ys: ys = new Unzip().apply(xs.tail).asInstanceOf[ys]
        private type ys = Unzip#apply[xs#tail]
        override  def apply: apply = Tuple2(Cons(x._1, ys._1.asInstanceOfList), Cons(x._2, ys._2.asInstanceOfList))
        override type apply = Tuple2[Cons[x#_1, ys#_1#asInstanceOfList], Cons[x#_2, ys#_2#asInstanceOfList]]
    }
}
