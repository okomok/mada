

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package set


private[dual]
object RemoveList {
     def apply[s <: Set, xs <: List](s: s, xs: xs): apply[s, xs] = xs.foldLeft(s, Step).asInstanceOfSet
    type apply[s <: Set, xs <: List]                             = xs#foldLeft[s, Step]#asInstanceOfSet

    val Step = new Step
    class Step extends Function2 {
        type self = Step
        override  def apply[b <: Any, a <: Any](b: b, a: a): apply[b, a] = b.asInstanceOfSet.remove(a)
        override type apply[b <: Any, a <: Any]                          = b#asInstanceOfSet#remove[a]
    }
}
