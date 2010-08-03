

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package seq; package iterator


final class Filter[it <: Iterator, f <: Function1](it: it, f: f) extends Iterator {
    type self = Filter[it, f]

    private lazy val jt: jt = new DropWhile(it, f.not)
    private type jt         =     DropWhile[it, f#not]

    override  def isEnd: isEnd = jt.isEnd
    override type isEnd        = jt#isEnd

    override  def deref: deref = jt.deref
    override type deref        = jt#deref

    override  def next: next = new DropWhile(jt, f.not)
    override type next       =     DropWhile[jt, f#not]
}
