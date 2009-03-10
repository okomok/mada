

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


/**
 * Contains utility methods operating on <code>String</code>.
 */
object Strings extends str.Conversions with str.Compatibles {
    /**
     * @return  <code>stringWrapper(s).elements</code>.
     */
    def elements(s: String): Iterator[Char] = stringWrapper(s).elements

    /**
     * @return  <code>this</code>.
     */
    val Compatibles: str.Compatibles = this
}
