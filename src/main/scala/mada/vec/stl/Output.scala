

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec.stl


object Output {
    def apply[A](f: A => Any): Vector[A] = new OutputVector(f)
}

class OutputVector[A](f: A => Any) extends Vector[A] {
    override def size = throw new UnsupportedOperationException("OutputVector.size")
    override def update(i: Int, e: A) = f(e)
}


// maybe unneeded.
class OutputCounter(val start: Int) extends Vector[Any] {
    def this() = this(0)
    private var c = start
    override def size = throw new UnsupportedOperationException("OutputCounter.size")
    override def update(i: Int, e: Any) = c += 1
    def count = c
}
