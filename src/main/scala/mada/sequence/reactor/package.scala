

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package sequence


package object reactor {


    @aliasOf("Reactor")
    val Type = Reactor

    @aliasOf("Reactor")
    type Type[-A] = Reactor[A]


// constructors

    def by[A](f: A => Unit): Reactor[A] = By(f)

}
