

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.stack


@compatibles
trait Compatibles { this: Stack.type =>
    implicit def madaStackFromJDeque[A](from: java.util.Deque[A]): Stack[A] = fromJDeque(from)
    implicit def madaStackFromSStack[A](from: scala.collection.mutable.Stack[A]): Stack[A] = fromSStack(from)
    implicit def madaStackFromSArrayStack[A](from: scala.collection.mutable.ArrayStack[A]): Stack[A] = fromSArrayStack(from)
}
