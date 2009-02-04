

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


/**
 * Contains utility types and methods operating on <code>Product</code>.
 */
object Products {
    /**
     * Mixin providing value semantics to <code>Product</code>.
     */
    trait AsValue extends HashCode.OfRef { self: Product =>
        abstract override def equals(that: Any) = that match {
            case that: Product => Vector.from(self) == Vector.from(that)
            case _ => false
        }

        abstract override lazy val hashCode = {
            var i = 0
            Vector.from(self).foldLeft(0)({ (b, a) => i += 1; b + (a.hashCode * i * 7) })
        }

        override def hashCodeOfRef = super.hashCode
    }

    /**
     * Value semantics Int pair
     */
    case class RangeVal(private val i: Int, private val j: Int) extends Pair[Int, Int](i, j) with AsValue
}
