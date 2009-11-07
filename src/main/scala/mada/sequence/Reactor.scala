

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package sequence


trait Reactor[-A] {

    def onEnd: Unit

    def react(e: A): Unit

}
