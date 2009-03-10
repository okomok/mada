

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.stl


private[mada] object OutputBy {
    def apply[A](f: A => Unit): Vector[A] = new OutputBy(f)
}

private[mada] class OutputBy[A](f: A => Unit) extends OutputVector[A] {
    override def output(e: A) = f(e)
}
