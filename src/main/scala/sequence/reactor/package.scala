

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package sequence


package object reactor {


    @aliasOf("Reactor")
    val Type = Reactor

    @aliasOf("Reactor")
    type Type[-A] = Reactor[A]


// constructors

    @equivalentTo("new Reactor[A] { def onEnd = z(); def react(e: A) = f(e) }")
    def make[A](z: Unit => Unit, f: A => Unit): Reactor[A] = Make(z, f)
    // `z: => Unit` is rejected.


// conversion

    @conversion
    def fromActor[A](from: scala.actors.Actor): Reactor[A] = FromActor[A](from)

}
