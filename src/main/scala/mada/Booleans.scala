

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
     * Alias of <code>bool.Infix</code>
     */
    val Infix = bool.Infix
}
