

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.str


/**
 * Contains explicit conversions around <code>String</code>.
 */
trait Conversions {
    def toIterable(s: String): Iterable[Char] = Iterables.byName(stringWrapper(s).elements)
    def toRandomAccessSeq(s: String): RandomAccessSeq[Char] = stringWrapper(s)
}
