

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package list


private[mada] final class ConsPartition {
     def apply[x <: Any, xs <: List, f <: Function1](x: x, xs: xs, f: f): apply[x, xs, f] =
        `if`(f.apply(x).asInstanceOfBoolean, Then(x, xs, f), Else(x, xs, f)).apply.asInstanceOfProduct2
    type apply[x <: Any, xs <: List, f <: Function1] =
        `if`[f#apply[x]#asInstanceOfBoolean, Then[x, xs, f], Else[x, xs, f]]#apply#asInstanceOfProduct2

    case class Then[x <: Any, xs <: List, f <: Function1](x: x, xs: xs, f: f) extends Function0 {
        override  val self = this
        override type self = Then[x, xs, f]
        override  def apply: apply = Tuple2(Cons(x, r._1.asInstanceOfList), r._2)
        override type apply = Tuple2[Cons[x, r#_1#asInstanceOfList], r#_2]
        private lazy val r: r = xs.partition(f)
        private type r = xs#partition[f]
    }

    case class Else[x <: Any, xs <: List, f <: Function1](x: x, xs: xs, f: f) extends Function0 {
        override  val self = this
        override type self = Else[x, xs, f]
        override  def apply: apply = Tuple2(r._1, Cons(x, r._2.asInstanceOfList))
        override type apply = Tuple2[r#_1, Cons[x, r#_2#asInstanceOfList]]
        private lazy val r: r = xs.partition(f)
        private type r = xs#partition[f]
    }
}
