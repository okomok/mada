

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence


private[sequence]
final class AdjacentBuffer[A](capacity: Int) {
    Precondition.positive(capacity, "Reactive.adjacent")
    private[this] val impl = new java.util.ArrayList[A](capacity)
    def isFull: Boolean = impl.size == capacity
    def removeFirst: Unit = impl.remove(0)
    def addLast(x: A): Unit = { assert(!isFull); impl.add(x) }
    def toVector: Vector[A] = vector.from(impl).force
}
