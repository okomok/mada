

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package list


private[dual]
object HasTwoOrMore {
     def apply[xs <: List](xs: xs): apply[xs] =
        `if`(xs.isEmpty, const0(`false`), Else(xs)).apply.asInstanceOfBoolean
    type apply[xs <: List] =
        `if`[xs#isEmpty, const0[`false`], Else[xs]]#apply#asInstanceOfBoolean

    case class Else[xs <: List](xs: xs) extends Function0 {
        type self = Else[xs]
        override  def apply: apply = `if`(xs.tail.isEmpty, const0(`false`), const0(`true`)).apply
        override type apply        = `if`[xs#tail#isEmpty, const0[`false`], const0[`true`]]#apply
    }
}
