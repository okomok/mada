

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


object Closer extends (Reactive[_] => Unit) {
    override def apply(r: Reactive[_]) = r match {
        case Merge(l, r) => apply(l); apply(r)
        case r: Resource[_] => r.close
        case r: Adapter[_, _] => apply(r.underlying)
        case _ => ()
    }
}
