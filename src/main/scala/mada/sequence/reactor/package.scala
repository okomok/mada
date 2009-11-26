

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package sequence


package object reactor {


    @aliasOf("Reactor")
    val Type = Reactor

    @aliasOf("Reactor")
    type Type[-A] = Reactor[A]


// constructors

    @equivalentTo("new Reactor[A] { def onEnd = z(); def react(e: A) = f(e) }")
    def make[A](z: Unit => Unit, f: A => Unit): Reactor[A] = Make(z, f)

    @aliasOf("make(_ => (), f)")
    def make[A](f: A => Unit): Reactor[A] = make(_ => (), f)

    def React[A](f: A => Unit) = new Reactor[A] {
        override def onEnd = ()
        override def react(e: A) = f(e)

        def OnEnd(z: Unit => Unit) = new Reactor[A] {
            override def onEnd = z()
            override def react(e: A) = f(e)
        }
    }


// conversion

    @conversion
    def fromActor[A](from: scala.actors.Actor): Reactor[A] = FromActor[A](from)

}
