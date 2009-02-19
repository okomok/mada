

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


/**
 * Mutable <code>Peg</code> which is assignable in run-time.
 * As a result, this is used to define recursive grammars.
 *
 * @param   p   assigned to <code>self</code>
 */
class Rule[A](private var p: Peg[A]) extends PegProxy[A] with Proxies.Mutable[Peg[A]] {
    /**
     * @return   <code>this(null)</code>.
     */
    def this() = this(null)

    override def self = p
    override def :=(that: => Peg[A]) = p = that
    override def isEmptyProxy = p eq null

    /**
     * Alias of <code>:=</code>
     */
    final def ::=(that: Peg[A]): Unit = p = that

    /**
     * Alias of <code>:=</code>
     */
    final def <--(that: Peg[A]): Unit = p = that

    /**
     * Returns a shallow copy. (The <code>self</code> is not copied.)
     */
    override def clone: Rule[A] = new Rule(p)
}
