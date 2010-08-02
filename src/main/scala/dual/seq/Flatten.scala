

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package seq


private[mada] final class Flatten {
     def apply[it <: Iterator](it: it): apply[it] = Bind(Iter(it, f))
    type apply[it <: Iterator]                    = Bind[Iter[it, f]]

    case class Iter[it <: Iterator](it: it) extends Iterator {
        type self = Iter[it]

        private lazy val jt: jt = it.advanceWhile(IsEmptyLocal())
        private type jt         = it#advanceWhile[IsEmptyLocal]

        override  def isEnd: isEnd = jt.isEnd
        override type isEnd        = jt#isEnd

        private lazy val localIter: localIter = jt.deref.asInstanceOfSeq.begin
        private type localIter                = jt#deref#asInstanceOfSeq#begin

        override  def deref: deref = localIter.deref
        override type deref        = localIter#deref

        override  def next: next = Iter(new Append().applyIter(localIter.next, jt.next))
        override type next       = Iter[    Append#  applyIter[localIter#next, jt#next]]
    }

    case class IsEmptyLocal() extends Function1 {
        type self = IsEmptyLocal
        override  def apply[xs <: Any](xs: xs): apply[xs] = xs.asInstanceOfSeq.isEmpty
        override type apply[xs <: Any]                    = xs#asInstanceOfSeq#isEmpty
    }

}
