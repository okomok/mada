

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


trait VectorAdapter[Z, A] extends Vector[A] {
    def * : Vector[Z]
    def mapIndex(i: Long) = i

    override def size = *.size
    override def apply(i: Long): A = *(mapIndex(i)).asInstanceOf[A]
    override def update(i: Long, e: A): Unit = *(mapIndex(i)) = e.asInstanceOf[Z]

    final def base = *
}
