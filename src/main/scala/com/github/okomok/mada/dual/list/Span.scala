

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package list


private[mada] final class ConsSpan {
     def apply[xs <: List, f <: Function1](xs: xs, f: f): apply[xs, f] =
        `if`(f.apply(xs.head).asInstanceOfBoolean, Then(xs, f), const0(Tuple2(Nil, xs))).apply.asInstanceOfProduct2.asInstanceOf[apply[xs, f]]
    type apply[xs <: List, f <: Function1] =
        `if`[f#apply[xs#head]#asInstanceOfBoolean, Then[xs, f], const0[Tuple2[Nil, xs]]]#apply#asInstanceOfProduct2

    case class Then[xs <: List, f <: Function1](xs: xs, f: f) extends Function0 {
        override  val self = this
        override type self = Then[xs, f]
        override  def apply: apply = Tuple2(Cons(xs.head, r._1.asInstanceOfList), r._2)
        override type apply        = Tuple2[Cons[xs#head, r#_1#asInstanceOfList], r#_2]
        private lazy val r: r = xs.tail.span(f)
        private type r        = xs#tail#span[f]
    }
}
