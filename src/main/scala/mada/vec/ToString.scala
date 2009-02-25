

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


private[mada] object ToString {
    def apply[A](from: Vector[A]): String = from.toJclArrayList.toString

/* nightmare
    def apply[A](from: Vector[A]): String = {
        Vector.stringize(wrap(
            Vector.untokenize(from.map{ (e: A) => Vector.fromString(e.toString) }, Vector.fromString(", "))
        ))
    }
    private def wrap(from: Vector[Char]) = Vector.fromString("[").append(from.drop(2)).append(Vector.fromString("]"))
*/
}
