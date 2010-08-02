

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package seq


private[mada] final class Filter {
     def apply[it <: Iterator, f <: Function1](it: it, f: f): apply[it, f] = Bind(Iter(it, f))
    type apply[it <: Iterator, f <: Function1]                             = Bind[Iter[it, f]]

    case class Iter[it <: Iterator, f <: Function1](it: it, f: f) extends Iterator {
        type self = Iter[it, f]

        private lazy val jt: jt = it.advanceWhile(f.not)
        private type jt         = it#advanceWhile[f#not]

        override  def isEnd: isEnd = jt.isEnd
        override type isEnd        = jt#isEnd

        override  def deref: deref = f.apply(jt.deref)
        override type deref        = f#apply[jt#deref]

        override  def next: next = jt.advanceWhile(f.not)
        override type next       = jt#advanceWhile[f#not]
    }
}
