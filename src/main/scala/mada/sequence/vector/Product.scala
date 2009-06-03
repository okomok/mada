

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence.vector


private[mada] object FromProduct {
    def apply(from: Product): Vector[Any] = from match {
        case from: VectorProduct[_] => from.v.asVectorOf[Any]
        case _ => new ProductVector(from)
    }
}

private[mada] class ProductVector(val from: Product) extends Vector[Any] {
    override def start = 0
    override def end = from.productArity
    override def apply(i: Int) = from.productElement(i)
}


private[mada] object ToProduct {
    def apply[A](from: Vector[A]): Product = from match {
        case from: ProductVector => from.from
        case _ => new VectorProduct(from)
    }
}

private[mada] class VectorProduct[A](val v: Vector[A]) extends Product {
    override def productElement(n: Int) = v.nth(n)
    override def productArity = v.nth.size
    override def productPrefix = "VectorProduct"
}
