

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.stack


class Compatible[A](val _1: Stack[A])

object Compatible {
    implicit def madaStackFromJDeque[A](from: java.util.Deque[A]): Compatible[A] = new Compatible(fromJDeque(from))
    implicit def madaStackFromSStack[A](from: scala.collection.mutable.Stack[A]): Compatible[A] = new Compatible(fromSStack(from))
    implicit def madaStackFromSArrayStack[A](from: scala.collection.mutable.ArrayStack[A]): Compatible[A] = new Compatible(fromSArrayStack(from))

    implicit def madaStackCompatibleSStack[A](from: Compatible[A]): scala.collection.mutable.Stack[A] = from._1.toSStack
}
