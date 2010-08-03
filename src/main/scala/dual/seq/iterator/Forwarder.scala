

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package seq; package iterator


trait Forwarder extends Iterator {
    protected  def delegate: delegate
    protected type delegate <: Iterator

    final override  def isEnd: isEnd = delegate.isEnd
    final override type isEnd        = delegate#isEnd

    final override  def deref: deref = delegate.deref
    final override type deref        = delegate#deref

    final override  def next: next = delegate.next
    final override type next       = delegate#next
}
