

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


private[mada] object ProductVector {
    def apply[A](from: Product): Vector[Any] = new ProductVector(from)
}

private[mada] class ProductVector(from: Product) extends Vector[Any] {
    override def start = 0
    override def end = from.productArity
    override def apply(i: Int) = from.productElement(i)
}
