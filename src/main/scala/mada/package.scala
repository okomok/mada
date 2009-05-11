

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package object mada {

    /**
     * Is mada in debug mode?
     */
    final val isDebug = true

    /**
     * Is mada in debug mode?
     */
    type isDebug = meta.`true`

    /**
     * @return  <code>!pre || post</code>.
     */
    def implies(pre: Boolean, post: => Boolean): Boolean = !pre || post

}