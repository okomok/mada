

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package seq


private[mada] final class Append {
     def apply[it <: Iterator, jt <: Iterator](it: it, jt: jt): apply[it, jt] = Bind(Iter(it, jt))
    type apply[it <: Iterator, jt <: Iterator]                                = Bind[Iter[it, jt]]

     def applyIter[it <: Iterator, jt <: Iterator](it: it, jt: jt): applyIter[it, jt] = Iter(it, jt)
    type applyIter[it <: Iterator, jt <: Iterator]

    case class Iter[it <: Iterator, jt <: Function1](it: it, jt: jt) extends Iterator {
        type self = Iter[it, jt]

        override  def isEnd: isEnd = it.isEnd  && jt.isEnd
        override type isEnd        = it#isEnd# &&[jt#isEnd]

        override  def deref: deref = `if`(it.isEnd, DerefThen(it, jt), DerefElse(it, jt)).apply.asInstanceOfSeqIterator
        override type deref        = `if`[it#isEnd, DerefThen[it, jt], DerefElse[it, jt]]#apply#asInstanceOfSeqIterator

        override  def next: next = `if`(it.isEnd, NextThen(it, jt), NextElse(it, jt)).apply.asInstanceOfSeqIterator
        override type next       = `if`[it#isEnd, NextThen[it, jt], NextElse[it, jt]]#apply#asInstanceOfSeqIterator
    }

    case class DerefThen[it <: Iterator, jt <: Iterator](it: it, jt: jt) extends Function0 {
        type self = DerefThen[it, jt]
        override  def apply: apply = jt.deref
        override type apply        = jt#deref
    }

    case class DerefElse[it <: Iterator, jt <: Iterator](it: it, jt: jt) extends Function0 {
        type self = DerefElse[it, jt]
        override  def apply: apply = it.deref
        override type apply        = it#deref
    }

    case class NextThen[it <: Iterator, jt <: Iterator](it: it, jt: jt) extends Function0 {
        type self = NextThen[it, jt]
        override  def apply: apply = Iter(it, jt.next)
        override type apply        = Iter[it, jt.next]
    }

    case class NextElse[it <: Iterator, jt <: Iterator](it: it, jt: jt) extends Function0 {
        type self = NextElse[it, jt]
        override  def apply: apply = Iter(it.next, jt)
        override type apply        = Iter[it#next, jt]
    }
}
