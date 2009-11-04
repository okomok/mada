

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package sequence


trait Reactor[-A] {

    def onEnd: Unit = ()

    def react(e: A): Unit

    def reactThrown(x: Throwable): Unit = () // really needed??

    /*
    def onIsEnd(b: Boolean): Unit

    def onDeref(e: Either[A, Throwable]): Unit = e match {
        case Left(a) => react(a)
        case Right(b) => reactThrown(b)
    }

    def onIncrement: Unit
    */

    final def reactTry(e: => A): Unit = {
        var v: A = _
        var thrown = false
        try {
            v = e // assignment may throw?
        } catch {
            case x => reactThrown(e); thrown = true
        }
        if (!thrown) {
            react(e)
        }
    }

}
