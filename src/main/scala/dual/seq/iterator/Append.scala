

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package seq; package iterator


final class Append[it <: Iterator, jt <: Function1](it: it, jt: jt) extends Iterator {
    type self = Append[it, jt]

    override  def isEnd: isEnd = it.isEnd  && jt.isEnd
    override type isEnd        = it#isEnd# &&[jt#isEnd]

    override  def deref: deref = `if`(it.isEnd, new DerefThen, new DerefElse).apply.asInstanceOfSeqIterator
    override type deref        = `if`[it#isEnd,     DerefThen,     DerefElse]#apply#asInstanceOfSeqIterator

    override  def next: next = `if`(it.isEnd, new NextThen, new NextElse).apply.asInstanceOfSeqIterator
    override type next       = `if`[it#isEnd,     NextThen,     NextElse]#apply#asInstanceOfSeqIterator

    private class DerefThen extends Function0 {
        type self = DerefThen
        override  def apply: apply = jt.deref
        override type apply        = jt#deref
    }

    private class DerefElse extends Function0 {
        type self = DerefElse
        override  def apply: apply = it.deref
        override type apply        = it#deref
    }

    private class NextThen extends Function0 {
        type self = NextThen
        override  def apply: apply = new Append(it, jt.next)
        override type apply        =     Append[it, jt.next]
    }

    private class NextElse extends Function0 {
        type self = NextElse
        override  def apply: apply = new Append(it.next, jt)
        override type apply        =     Append[it#next, jt]
    }
}
