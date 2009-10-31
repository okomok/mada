

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package sequence; package iterative


/**
 * Helps to implement recursive sequences.
 */
@deprecated("use List instead")
class Recursive[A] extends Iterative[A] {
    @volatile private var f: Function0[Iterative[A]] = null

    /**
     * Assigns <code>that</code>.
     */
    def :=(that: => Iterative[A]): Unit = {
        f = util.byLazy(that)
    }

    // For Recursive to correctly work,
    // Iterative constructors shall not call begin of underlying one.
    // Otherwise, it simply results in stack overflow by infinite begins.
    override def begin: Iterator[A] = f().begin
}


@deprecated("use List instead")
class RecursiveForwarder[A] extends Forwarder[A] {
    @volatile private var f: Function0[Iterative[A]] = null

    /**
     * Assigns <code>that</code>.
     */
    def :=(that: => Iterative[A]): Unit = {
        f = util.byLazy(that)
    }

    override protected def delegate = f()
}
