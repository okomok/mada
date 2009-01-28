

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


/**
 * Contains utility methods operating on <code>String</code>.
 */
object Strings {
    /**
     * Provides implicit conversions around <code>String</code>.
     */
    val Compatibles = new {
        implicit def string2iterator(str: String): Iterator[Char] = iterator(str)
    }

    /**
     * Converts to <code>Iterator</code>. Note "abc".elements needs implicit conversion to <code>RichString</code>.
     */
    def elements(str: String): Iterator[Char] = str.elements

    /**
     * Alias of this.elements
     */
    def iterator(str: String): Iterator[Char] = str.elements
}
