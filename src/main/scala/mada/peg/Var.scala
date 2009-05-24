

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


/**
 * Mutable <code>Peg</code> which is assignable in run-time.
 * As a result, this is used to define recursive grammars.
 *
 * @param   p   assigned to <code>delegate</code>
 */
class Var[A](@volatile private var p: Peg[A]) extends Forwarder[A] with util.Var[Peg[A]] {
    def this() = this(null)

    override protected def delegate = p
    override def assign(that: Peg[A]) = { p = that }

    @aliasOf("assign")
    final def ::=(that: Peg[A]): Unit = { p = that }

    @aliasOf("assign")
    final def <--(that: Peg[A]): Unit = { p = that }

    /**
     * Returns a shallow copy. (The <code>delegate</code> is not copied.)
     */
    override def clone: Var[A] = new Var(p)
}
