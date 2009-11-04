

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package sequence


trait Reactive[-A] { self =>

    def subscribe(t: Reactor[A]): Unit

    def map[A](f: A => B): Reactive[B] = Map(this, f)

    def filter(p: A => Boolean): Reactive[A] = Filter(this, p)

    def foreach(f: A => Unit): Reactive[A] = new Reactive[A] {
        override def subscribe(t: Reactor[A]) = {
            self.subscribe(new Reactor[A] {
                override react(e: A) = { f(e); t.react(e) }
            })
        }
    }

    def takeWhile(p: A => Boolean): Reactive[A] = new Reactive[A] {
        override def subscribe(t: Reactor[A]) = {
            self.subscribe(new Reactor[A] {
                override react(e: A) = { f(e); t.react(e) }
            })
        }
    }

    /*
    def zip[B](that: Reactive[B]): Reactive[(A, B)] = new Reactive[B] {
        override def subscrive(x: Reactor[(A, B)]): Unit = {
            self.subscribe(new Reactor[A] {
                override def onDeref(x: A) = x.??
            })
            that.subscrive(new Reactor[B] {
                override def onDeref(x: B) = x.??
            })
        }
    }
    */

}
