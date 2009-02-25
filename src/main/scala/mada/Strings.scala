

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


/**
 * Contains utility methods operating on <code>String</code>.
 */
object Strings extends str.Conversions with str.Compatibles {
    /**
     * Alias of <code>toIterator</code>
     */
    def elements(s: String): Iterator[Char] = toIterator(s)

    /**
     * @return  <code>this</code>.
     */
    val Compatibles: str.Compatibles = this
}
