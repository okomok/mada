

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


package object string {

    /**
     * Concatenates all the arguments of a given sequence of objects.
     */
    def flatten(ss: sequence.Iterative[Any]): String = {
        val sb = new StringBuilder
        for (s <- ss) {
            sb.append(s.toString)
        }
        sb.toString
    }

    /**
     * @return  <code>flatten(ss)</code>.
     */
    def concat(ss: Any*): String = flatten(ss)

}
