

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
    object Infix {
        /**
         * Intermediate class for infix operators.
         */
        sealed class MadaBooleans(_1: Boolean) {
            /**
             * @return  <code>Booleans.implies(_2)</code>.
             */
            def implies(_2: => Boolean) = Booleans.implies(_1, _2)
        }

        /**
         * @return  <code>new MadaBooleans(_1)</code>.
         */
        implicit def madaBooleanToMadaBooleans(_1: Boolean): MadaBooleans = new MadaBooleans(_1)
    }
}
