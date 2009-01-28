

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
     * Hash code of reference (value semantics ignored.)
     */
    def ofRef(x: OfRef): Int = x.hashCodeOfRef

    /**
     * Required for <code>ofRef</code>.
     */
    trait OfRef {
        /**
         * Shall return <code>super.hashCode</code> in subclass.
         */
        def hashCodeOfRef: Int
    }
}
