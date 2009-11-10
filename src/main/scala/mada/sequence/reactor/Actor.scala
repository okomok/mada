

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package sequence; package reactor


import scala.actors.Actor


case object OnEnd
case class React[+A](_1: A)


case class FromActor[A](_1: Actor) extends Reactor[A] {
    override def onEnd = _1 ! OnEnd
    override def react(e: A) = _1 ! React(e)
}

case class ToActor[-A](_1: Reactor[A]) extends Actor {
    override def act = {
        Actor.loop {
            react {
                case OnEnd => _1.onEnd
                case React(e) => _1.react(e.asInstanceOf[A])
            }
        }
    }
}
