

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec2


trait Adapter[Z, A] extends Vector[A] {
    def * : Vector[Z]
    override def size = *.size
    override def apply(i: Long): A = *(i).asInstanceOf[A]
    override def update(i: Long, e: A): Unit = *(i) = e.asInstanceOf[Z]
}


trait NotWritable[A] extends Vector[A] {
    override def update(i: Long, e: A): Unit = throw new NotWritableError(this)
}
