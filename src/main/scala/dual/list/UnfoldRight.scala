

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package list


private[list]
final class UnfoldRight[z <: Any, f <: Function1](z: z, f: f) extends AbstractList {
    type self = UnfoldRight[z, f]

    private lazy val acc: acc = f.apply(z).asInstanceOfOption
    private type acc          = f#apply[z]#asInstanceOfOption

    override  def isEmpty: isEmpty = acc.isEmpty
    override type isEmpty          = acc#isEmpty

    override  def head: head = acc.get.asInstanceOfProduct2._1
    override type head       = acc#get#asInstanceOfProduct2#_1

    override  def tail: tail = new UnfoldRight(acc.get.asInstanceOfProduct2._2, f)
    override type tail       =     UnfoldRight[acc#get#asInstanceOfProduct2#_2, f]
}
