

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


/**
 * Contains utility methods for hash-code.
 */
object HashCode {
    /**
     * Hash-code of <code>Int</code>
     */
    def ofInt(n: Int): Int = n

    /**
     * Hash-code of <code>Long</code>
     */
    def ofLong(n: Long): Int = (n ^ (n >>> 32)).toInt
}
