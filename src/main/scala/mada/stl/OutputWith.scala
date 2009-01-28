

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.stl


private[mada] object OutputWith {
    def apply[A](f: A => Any): Vector[A] = new OutputWith(f)
}

private[mada] class OutputWith[A](f: A => Any) extends OutputVector[A] {
    override def output(e: A) = f(e)
}
