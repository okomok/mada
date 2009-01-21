

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


trait VectorAdapter[Z, A] extends Vector[A] {
    def * : Vector[Z]
    def mapIndex(i: Int) = i

    override def size = *.size
    override def apply(i: Int): A = *(mapIndex(i)).asInstanceOf[A]
    override def update(i: Int, e: A): Unit = *(mapIndex(i)) = e.asInstanceOf[Z]

    final def base = *
}
