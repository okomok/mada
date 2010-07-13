

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package list


private[mada] final case class Unzip[xs <: List](xs: xs) {
     def apply: apply = `if`(xs.isEmpty, always0(Tuple2(Nil, Nil)), Else()).apply.asInstanceOfProduct2
    type apply = `if`[xs#isEmpty, always0[Tuple2[Nil, Nil]], Else]#apply#asInstanceOfProduct2

    final case class Else() extends Function0 {
        override  val self = this
        override type self = Else
        private lazy val x: x = xs.head.asInstanceOfProduct2
        private type x = xs#head#asInstanceOfProduct2
        private lazy val ys: ys = Unzip(xs.tail).apply.asInstanceOf[ys]
        private type ys = Unzip[xs#tail]#apply
        override  def apply: apply = Tuple2(Cons(x._1, ys._1.asInstanceOfList), Cons(x._2, ys._2.asInstanceOfList))
        override type apply = Tuple2[Cons[x#_1, ys#_1#asInstanceOfList], Cons[x#_2, ys#_2#asInstanceOfList]]
    }
}
