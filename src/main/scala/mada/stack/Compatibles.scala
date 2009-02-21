

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.stack


/**
 * Contains implicit conversions around <code>Stack</code>.
 */
trait Compatibles {
    import Stack._

    implicit def madaStackFromJclDeque[A](from: java.util.Deque[A]) = fromJclDeque(from)
    implicit def madaStackFromSclStack[A](from: scala.collection.mutable.Stack[A]) = fromSclStack(from)
    implicit def madaStackFromSclArrayStack[A](from: scala.collection.mutable.ArrayStack[A]) = fromSclArrayStack(from)
}
