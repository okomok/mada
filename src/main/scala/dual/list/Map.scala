

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package list


private[dual]
object Map {
     def apply[xs <: List, f <: Function1](xs: xs, f: f): apply[xs, f] = new Impl(xs, f)
    type apply[xs <: List, f <: Function1]                             =     Impl[xs, f]

    class Impl[xs <: List, f <: Function1](xs: xs, f: f) extends AbstractList {
        type self = Impl[xs, f]

        override  def isEmpty: isEmpty = xs.isEmpty
        override type isEmpty          = xs#isEmpty

        override  def head: head = f.apply(xs.head)
        override type head       = f#apply[xs#head]

        override  def tail: tail = new Impl(xs.tail, f)
        override type tail       =     Impl[xs#tail, f]
    }
}
