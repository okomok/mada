

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package seq; package iterator


final class Flatten[it <: Iterator](it: it) extends Iterator {
    type self = Flatten[it]

    private lazy val jt: jt = new DropWhile(it, new IsEmptyLocal)
    private type jt         =     DropWhile[it,     IsEmptyLocal]

    override  def isEnd: isEnd = jt.isEnd
    override type isEnd        = jt#isEnd

    private lazy val localIter: localIter = jt.deref.asInstanceOfSeq.begin
    private type localIter                = jt#deref#asInstanceOfSeq#begin

    override  def deref: deref = localIter.deref
    override type deref        = localIter#deref

    override  def next: next = new Flatten(new Append(localIter.next, jt.next))
    override type next       =     Flatten[    Append[localIter#next, jt#next]]

    private class IsEmptyLocal extends Function1 {
        type self = IsEmptyLocal
        override  def apply[xs <: Any](xs: xs): apply[xs] = xs.asInstanceOfSeq.isEmpty
        override type apply[xs <: Any]                    = xs#asInstanceOfSeq#isEmpty
    }
}
