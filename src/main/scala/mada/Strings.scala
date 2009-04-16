

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


/**
 * Contains utility methods operating on strings.
 */
object Strings {
    /**
     * Concatenates all the arguments of a given iterable of objects.
     */
    def flatten(ss: Iterable[Any]): String = {
        val sb = new StringBuilder
        for (s <- ss.projection) {
            sb.append(s.toString)
        }
        sb.toString
    }

    /**
     * @return  <code>flatten(ss)</code>.
     */
    def concat(ss: Any*): String = flatten(ss)
}
