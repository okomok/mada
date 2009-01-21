

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


trait NotWritable[A] extends Vector[A] {
    override def update(i: Int, e: A): Unit = throw new NotWritableError(this)
    override def readOnly = this
}
