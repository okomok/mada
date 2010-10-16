

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual


/**
 * Mixin for a metatype whose `equals` is reference-equality.
 */
trait ReferenceEquality extends Any {
    override def equals(that: scala.Any) = refEquals(that)
    override def hashCode = refHashCode
    override def toString = refToString
    override def canEqual(that: scala.Any): scala.Boolean = throw new UnsupportedOperationException("ReferenceEquality.canEqual")
}
