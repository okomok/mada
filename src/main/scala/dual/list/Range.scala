

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package list


private[dual]
object Range {
     def apply[n <: Nat, m <: Nat](n: n, m: m): apply[n, m] = new Impl(n, m)
    type apply[n <: Nat, m <: Nat]                          =     Impl[n, m]

    class Impl[n <: Nat, m <: Nat](n: n, m: m) extends AbstractList {
        type self = Impl[n, m]

        override  def isEmpty: isEmpty = n  === m
        override type isEmpty          = n# ===[m]

        override  def head: head = n
        override type head       = n

        override  def tail: tail = new Impl(n.increment, m)
        override type tail       =     Impl[n#increment, m]
    }
}
