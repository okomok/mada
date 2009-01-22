

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object ProductVector {
    def apply[A](from: Product): Vector[Any] = new ProductVector(from)
}

class ProductVector(from: Product) extends Vector[Any] {
    override def size = from.productArity
    override def apply(i: Int) = from.productElement(i)
}
