

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package list


private[mada] final class ConsDropWhile {
     def apply[xs <: List, f <: Function1](xs: xs, f: f): apply[xs, f] =
        `if`(f.apply(xs.head).asInstanceOfBoolean, Then(xs, f), const0(xs)).apply.asInstanceOfList.asInstanceOf[apply[xs, f]]
    type apply[xs <: List, f <: Function1] =
        `if`[f#apply[xs#head]#asInstanceOfBoolean, Then[xs, f], const0[xs]]#apply#asInstanceOfList

    case class Then[xs <: List, f <: Function1](xs: xs, f: f) extends Function0 {
        override  val self = this
        override type self = Then[xs, f]
        override  def apply: apply = xs.tail.dropWhile(f)
        override type apply = xs#tail#dropWhile[f]
    }
}
