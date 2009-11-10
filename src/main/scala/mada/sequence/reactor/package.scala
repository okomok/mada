

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package sequence


package object reactor {


    @aliasOf("Reactor")
    val Type = Reactor

    @aliasOf("Reactor")
    type Type[-A] = Reactor[A]


// constructors

    def make[A](z: => Unit, f: A => Unit): Reactor[A] = Make(util.byName(z), f)

// conversion

    @conversion
    def fromActor[A](from: scala.actors.Actor): Reactor[A] = FromActor[A](from)

}
