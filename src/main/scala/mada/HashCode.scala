

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


/**
 * Contains utility methods for hash code. Requires no heap-allocations.
 */
object HashCode {
    /**
     * Hash code of <code>Int</code>
     */
    def ofInt(x: Int): Int = x

    /**
     * Hash code of <code>Long</code>
     */
    def ofLong(x: Long): Int = (x ^ (x >>> 32)).toInt

    /**
     * @return  <code>java.lang.System.idenityHashCode(x)</code>.
     */
    def ofRef(x: AnyRef): Int = java.lang.System.identityHashCode(x)
}
