

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package sequence; package reactive


case class BeforeReactException[A](_1: Throwable) extends Exception


private object BeforeReact {
    def try_[A](e: => A): A = {
        try {
            e
        } catch {
            case x => throw BeforeReactException(x)
        }
    }
}
