

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package list


final class Range[n <: Nat, m <: Nat](n: n, m: m) extends AbstractList {
    type self = Range[n, m]

    override  def isEmpty: isEmpty = n  === m
    override type isEmpty          = n# ===[m]

    override  def head: head = n
    override type head       = n

    override  def tail: tail = new Range(n.increment, m)
    override type tail       =     Range[n#increment, m]
}
