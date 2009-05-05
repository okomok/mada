

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


private[mada] object Writer {
    def apply[A](v: Vector[A]): Writer[A] = apply(v, v.start)
    def apply[A](v: Vector[A], i: Int): Writer[A] = new Writer(v, i)
}

/**
 * A function which writes into <code>out</code> from <code>start</code>.
 * Considered as unrestrictive <code>Vector.copyTo</code>.
 *
 * @param   start   the initial value of internal index.
 */
class Writer[A](val out: Vector[A], val start: Int) extends (A => Unit) {
    private var i = start

    /**
     * @return  <code>out(i) = e; i += 1</code>, where <code>i</code> is the internal index.
     */
    override def apply(e: A): Unit = { out(i) = e; i += 1 }

    /**
     * @return  the internal index.
     */
    final def end: Int = i
}
