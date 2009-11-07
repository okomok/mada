

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package sequence


// See: scala.Responder


import reactive._


trait Reactive[+A] {

    def subscribe(k: Reactor[A]): Unit

    def map[B](f: A => B): Reactive[B] = Map(this, f)

    def flatMap[B](f: A => Reactive[B]): Reactive[B] = FlatMap(this, f)

    def filter(p: A => Boolean): Reactive[A] = Filter(this, p)

    def foreach(f: A => Unit): Unit = {
        val k = new Reactor[A] {
            override def onEnd = ()
            override def react(e: A) = f(e)
        }
        subscribe(k)
    }

}



object Reactive {








}
