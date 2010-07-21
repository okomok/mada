

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package sequence; package vector


private[mada] case class FromProduct(_1: Product) extends Forwarder[Any] {
    override protected val delegate = _1 match {
        case _1: ToProduct => _1.a1.asVectorOf[Any] // from-to fusion
        case _ => new _FromProduct(_1)
    }
}

private class _FromProduct(_1: Product) extends Vector[Any] {
    override def start = 0
    override def end = _1.productArity
    override def apply(i: Int) = _1.productElement(i)

    override def toProduct = _1 // to-from fusion
}


// A `case class` is an implicit subclass of Product.
class ToProduct(val a1: Vector[_]) extends Product {
    override def productElement(n: Int): Any = a1.nth(n)
    override def productArity = a1.nth.size
    override def productPrefix = "VectorProduct"

    override def canEqual(that: Any) = that.isInstanceOf[ToProduct]
}
