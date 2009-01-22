

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object ToString {
    def apply[A](from: Vector[A]): String = from.toJclArrayList.toString

/* nightmare
    def apply[A](from: Vector[A]): String = {
        Vector.stringize(wrap(
            Vector.untokenize(from.map({ (e: A) => Vector.stringVector(e.toString) }), Vector.stringVector(", "))
        ))
    }
    private def wrap(from: Vector[Char]) = Vector.stringVector("[").append(from.drop(2)).append(Vector.stringVector("]"))
*/
}
