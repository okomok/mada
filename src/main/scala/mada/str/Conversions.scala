

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.str


/**
 * Contains explicit conversions around <code>String</code>.
 */
trait Conversions {
    def toIterator(s: String): Iterator[Char] = s.elements
}
