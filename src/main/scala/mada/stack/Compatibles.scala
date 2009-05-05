

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.stack


/**
 * Contains implicit conversions around <code>Stack</code>.
 */
trait Compatibles { this: Stack.type =>
    @returnthis val Compatibles: Compatibles = this
    implicit def madaStackFromJclDeque[A](from: java.util.Deque[A]): Stack[A] = fromJclDeque(from)
    implicit def madaStackFromSclStack[A](from: scala.collection.mutable.Stack[A]): Stack[A] = fromSclStack(from)
    implicit def madaStackFromSclArrayStack[A](from: scala.collection.mutable.ArrayStack[A]): Stack[A] = fromSclArrayStack(from)
}
