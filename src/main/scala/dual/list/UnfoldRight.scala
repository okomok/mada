

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package list


private[dual]
object UnfoldRight {
     def apply[z <: Any, f <: Function1](z: z, f: f): apply[z, f] = new Impl(z, f)
    type apply[z <: Any, f <: Function1]                          =     Impl[z, f]

    class Impl[z <: Any, f <: Function1](z: z, f: f) extends AbstractList {
        type self = Impl[z, f]

        private lazy val acc: acc = f.apply(z).asInstanceOfOption
        private type acc          = f#apply[z]#asInstanceOfOption

        override  def isEmpty: isEmpty = acc.isEmpty
        override type isEmpty          = acc#isEmpty

        override  def head: head = acc.get.asInstanceOfProduct2._1
        override type head       = acc#get#asInstanceOfProduct2#_1

        override  def tail: tail = new Impl(acc.get.asInstanceOfProduct2._2, f)
        override type tail       =     Impl[acc#get#asInstanceOfProduct2#_2, f]
    }
}
