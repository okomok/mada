

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

    /**
     * Hash code of <code>Int</code>
     */
    def hashCodeOfInt(x: Int): Int = x

    /**
     * Hash code of <code>Long</code>
     */
    def hashCodeOfLong(x: Long): Int = (x ^ (x >>> 32)).toInt

    /**
     * @return  <code>java.lang.System.idenityHashCode(x)</code>.
     */
    def hashCodeOfRef(x: AnyRef): Int = java.lang.System.identityHashCode(x)

    /**
     * Typed <code>null</code>
     */
    def nullOf[A >: Null]: A = null

    /**
     * Typed <code>None</code>
     */
    def NoneOf[A]: Option[A] = None

}
