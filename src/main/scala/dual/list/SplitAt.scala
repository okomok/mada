

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package list


private[mada] final class ConsSplitAt {
     def apply[xs <: List, n <: Nat](xs: xs, n: n): apply[xs, n] =
        `if`(n.isZero, const0(Tuple2(Nil, xs)), Else(xs, n)).apply.asInstanceOfProduct2//.asInstanceOf[apply[xs, n]]
    type apply[xs <: List, n <: Nat] =
        `if`[n#isZero, const0[Tuple2[Nil, xs]], Else[xs, n]]#apply#asInstanceOfProduct2

    case class Else[xs <: List, n <: Nat](xs: xs, n: n) extends Function0 {
        override  val self = this
        override type self = Else[xs, n]
        override  def apply: apply = Tuple2(Cons(xs.head, r._1.asInstanceOfList), r._2)
        override type apply = Tuple2[Cons[xs#head, r#_1#asInstanceOfList], r#_2]
        private lazy val r: r = xs.tail.splitAt(n.decrement)
        private type r = xs#tail#splitAt[n#decrement]
    }
}
