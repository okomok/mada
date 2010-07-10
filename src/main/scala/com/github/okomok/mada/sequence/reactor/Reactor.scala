

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package sequence; package reactor


object Reactor extends Common


trait Reactor[-A] { self =>

    /**
     * Called when sequence ends.
     */
    def onEnd: Unit

    /**
     * Called when an element passed.
     */
    def react(e: A): Unit

    /**
     * Suppresses <code>onEnd</code>.
     */
    final def noEnd: Reactor[A] = NoEnd(this)

    // TODO
    final def synchronize: Reactor[A] = Synchronize(this)

    @conversion
    final def toActor: scala.actors.Actor = ToActor(this)

}
