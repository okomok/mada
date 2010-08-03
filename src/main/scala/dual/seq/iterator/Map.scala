

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package seq; package iterator


final class Map[it <: Iterator, f <: Function1](it: it, f: f) extends Iterator {
    type self = Map[it, f]

    override  def isEnd: isEnd = it.isEnd
    override type isEnd        = it#isEnd

    override  def deref: deref = f.apply(it.deref)
    override type deref        = f#apply[it#deref]

    override  def next: next = new Map(it.next, f)
    override type next       =     Map[it#next, f]
}
