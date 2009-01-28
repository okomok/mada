

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


/**
 * Contains utility methods operating on <code>Boolean</code>.
 */
object Booleans {
    /**
     * @return  <code>!pre || post</code>.
     */
    def implies(pre: Boolean, post: => Boolean): Boolean = !pre || post

    /**
     * Provides infix operators using implicit conversions.
     */
    val Infix = new {
        class MadaBooleans(_1: Boolean) {
            def implies(_2: => Boolean) = Booleans.implies(_1, _2)
        }
        implicit def boolean2MadaBooleans(_1: Boolean): MadaBooleans = new MadaBooleans(_1)
    }
}
