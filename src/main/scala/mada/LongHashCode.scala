

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


object LongHashCode {
    def apply(n: Long): Int = (n ^ (n >>> 32)).toInt
}
