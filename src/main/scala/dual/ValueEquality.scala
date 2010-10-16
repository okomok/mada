

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual


/**
 * Mixin for a metatype whose `equals` is value-equality.
 */
trait ValueEquality extends Any {
    override def equals(that: scala.Any) = that match {
        case that: Any => (this eq that) || (that canEqual this) && (undual == that.undual)
        case _ => false
    }
    override def hashCode = undual.hashCode
    override def toString = "dual." + undual.toString
}
