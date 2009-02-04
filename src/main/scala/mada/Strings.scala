

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


/**
 * Contains utility methods operating on <code>String</code>.
 */
object Strings {
    /**
     * @return  <code>str.elements</code>. Note "abc".elements needs implicit conversion to <code>RichString</code>.
     */
    def toIterator(str: String): Iterator[Char] = str.elements

    /**
     * Alias of <code>toIterator</code>
     */
    def elements(str: String): Iterator[Char] = toIterator(str)

    /**
     * Provides implicit conversions around <code>String</code>.
     */
    object Compatibles {
        /**
         * @return  <code>toIterator(str)</code>.
         */
        implicit def string2iterator(str: String): Iterator[Char] = toIterator(str)
    }
}
