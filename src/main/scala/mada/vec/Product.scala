

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object ProductVector {
    def apply[A](u: Product): Vector[Any] = new ProductVector(u)
}

class ProductVector(product: Product) extends Vector[Any] {
    override def size = product.productArity
    override def apply(i: Long) = product.productElement(i.toInt)
}
