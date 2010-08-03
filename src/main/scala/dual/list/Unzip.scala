

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package list


final class Unzip1[xs <: List](xs: xs) extends AbstractList {
    type self = Unzip1[xs]

    override  def isEmpty: isEmpty = xs.isEmpty
    override type isEmpty          = xs#isEmpty

    override  def head: head = xs.head.asInstanceOfProduct2._1
    override type head       = xs#head#asInstanceOfProduct2#_1

    override  def tail: tail = new Unzip1(xs.tail)
    override type tail       =     Unzip1[xs#tail]
}

final class Unzip2[xs <: List](xs: xs) extends AbstractList {
    type self = Unzip2[xs]

    override  def isEmpty: isEmpty = xs.isEmpty
    override type isEmpty          = xs#isEmpty

    override  def head: head = xs.head.asInstanceOfProduct2._2
    override type head       = xs#head#asInstanceOfProduct2#_2

    override  def tail: tail = new Unzip2(xs.tail)
    override type tail       =     Unzip2[xs#tail]
}
