

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package seq


sealed abstract class Nil extends AbstractSeq {
    type self = Nil

    override  def isEmpty: isEmpty = `true`
    override type isEmpty          = `true`

    override  def head: head = noSuchElement("seq.Nil.head")
    override type head       = noSuchElement[_]

    override  def tail: tail = noSuchElement("seq.Nil.tail")
    override type tail       = noSuchElement[_]
}

private[seq] object _Nil {
    val value = new Nil{}
}
