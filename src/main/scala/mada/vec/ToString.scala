

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


private[mada] object ToString {
    def apply[A](from: Vector[A]): String = from.toJclArrayList.toString

/* nightmare
    def apply[A](from: Vector[A]): String = {
        Vector.stringize(wrap(
            Vector.unsplit(from.map{ (e: A) => Vector.unstringize(e.toString) })(Vector.unstringize(", "))
        ))
    }
    private def wrap(from: Vector[Char]) = Vector.unstringize("[").append(from.drop(2)).append(Vector.unstringize("]"))
*/
}
