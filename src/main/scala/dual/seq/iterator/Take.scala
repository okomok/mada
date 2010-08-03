

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package seq; package iterator


final class Take[it <: Iterator, n <: Nat](it: it, n: n) extends Iterator {
    type self = Take[it, n]

    private lazy val jt: jt = `if`(n.isZero, Const0(End), Const0(it)).apply
    private type jt         = `if`[n#isZero, Const0[End], Const0[it]]#apply

    override  def isEnd: isEnd = jt.isEnd
    override type isEnd        = jt#isEnd

    override  def deref: deref = jt.deref
    override type deref        = jt#deref

    override  def next: next = new Take(it.next, n.decrement)
    override type next       =     Take[it#next, n#decrement]
}


final class TakeWhile[it <: Iterator, f <: Nat](it: it, n: n) extends Iterator {
    type self = Take[it, n]

    private lazy val jt: jt = `if`(it.isEnd, Const0(it), Const0(it)).apply
    private type jt         = `if`[n#isZero, Const0[End], Const0[it]]#apply

    override  def isEnd: isEnd = jt.isEnd
    override type isEnd        = jt#isEnd

    override  def deref: deref = jt.deref
    override type deref        = jt#deref

    override  def next: next = new Take(it.next, f)
    override type next       =     Take[it#next, f]
}
