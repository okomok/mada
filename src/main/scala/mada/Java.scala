

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


/**
 * Contains utility methods for java.
 */
object Java {
    /**
     * @return  <code>if (null == e) None else Some(e)</code>.
     */
    def toOption[A](e: A): Option[A] = if (null == e) None else Some(e)

    /**
     * @return  <code>if (e.isEmpty) fromNone else e.get</code>.
     */
    def fromOption[A](e: Option[A]): A = if (e.isEmpty) fromNone[A] else e.get

    /**
     * @return  <code>null.asInstanceOf[A]</code>.
     */
    def fromNone[A]: A = null.asInstanceOf[A]
}
