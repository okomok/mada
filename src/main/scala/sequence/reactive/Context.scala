

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


object Async extends ((=> Unit) => Unit) {
    override def apply(f: => Unit) = eval.Async(f)
}

object InEdt extends ((=> Unit) => Unit) {
    override def apply(f: => Unit) = eval.InEdt(f)
}

object Strict extends ((=> Unit) => Unit) {
    override def apply(f: => Unit) = eval.Strict(f)
}

object Threaded extends ((=> Unit) => Unit) {
    override def apply(f: => Unit) = eval.Threaded(f)
}
