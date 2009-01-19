

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object FromValues {
    def apply[A](es: A*): Vector[A] = Vector.fromIterator(es.elements)
}
