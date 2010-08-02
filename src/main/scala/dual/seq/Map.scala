

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package seq


private[mada] final class Map {
     def apply[it <: Iterator, f <: Function1](it: it, f: f): apply[it, f] = Bind(Iter(it, f))
    type apply[it <: Iterator, f <: Function1]                             = Bind[Iter[it, f]]

    case class Iter[it <: Iterator, f <: Function1](it: it, f: f) extends Iterator {
        type self = Iter[it, f]

         def isEnd: isEnd = it.isEnd
        type isEnd        = it#isEnd

         def deref: deref = f.apply(it.deref)
        type deref        = f#apply[it#deref]

         def next: next = Iter(it.next, f)
        type next       = Iter[it#next, f]
    }
}
