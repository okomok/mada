

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


// unneeded if NVI were known in the java world.

class BoundsVector[A](override val * : Vector[A]) extends Adapter[A, A] {
    override def mapIndex(i: Long) = {
        if (i < 0 || i >= *.size) {
            throw new IndexOutOfBoundsException(i.toString)
        }
        i
    }
}
