

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


/**
 * Mutable <code>Peg</code> which is assignable in run-time.
 * As a result, this is used to define recursive grammars.
 *
 * @param   p   assigned to <code>self</code>
 */
class Rule[A](private var p: Peg[A]) extends Forwarder[A] with Proxies.Mutable[Peg[A]] {
    /**
     * Constructs null proxy.
     */
    def this() = this(null)

    override def self = p
    override def assign(that: => Peg[A]) = p = that
    override def resign = p = null
    override def isNull = p eq null

    @aliasOf("assign")
    final def ::=(that: Peg[A]): Unit = p = that

    @aliasOf("assign")
    final def <--(that: Peg[A]): Unit = p = that

    /**
     * Returns a shallow copy. (The <code>self</code> is not copied.)
     */
    override def clone: Rule[A] = new Rule(p)
}
