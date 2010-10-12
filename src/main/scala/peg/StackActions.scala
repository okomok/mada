

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package peg


/**
 * Helper for stack evaluations.
 */
class StackActions[A, B](override val delegate: Stack[B]) extends stack.Forwarder[B] {


// constructors

    /**
     * Constructs from an empty stack.
     */
    def this() = this(Stack.from(new java.util.ArrayDeque[B]))


// apply

    @aliasOf("eval0")
    final def apply(f: Function1[sequence.Vector[A], B]): Action[A] = eval0(f)

    @aliasOf("eval1")
    final def apply(f: Function2[sequence.Vector[A], B, B]): Action[A] = eval1(f)

    @aliasOf("eval2")
    final def apply(f: Function3[sequence.Vector[A], B, B, B]): Action[A] = eval2(f)

    @aliasOf("eval3")
    final def apply(f: Function4[sequence.Vector[A], B, B, B, B]): Action[A] = eval3(f)


// eval

    /**
     * Pushes the result of applying region to <code>f</code>.
     */
    def eval0(f: Function1[sequence.Vector[A], B]): Action[A] = new Action[A] {
        override def apply(v: sequence.Vector[A]) = {
            delegate.push(f(v))
        }
    }

    /**
     * Evaluates stack elements.
     */
    def eval1(f: Function2[sequence.Vector[A], B, B]): Action[A] = new Action[A] {
        override def apply(v: sequence.Vector[A]) = {
            val e1 = delegate.pop
            delegate.push(f(v, e1))
        }
    }

    /**
     * Evaluates stack elements.
     */
    def eval2(f: Function3[sequence.Vector[A], B, B, B]): Action[A] = new Action[A] {
        override def apply(v: sequence.Vector[A]) = {
            val e1 = delegate.pop
            val e2 = delegate.pop
            delegate.push(f(v, e2, e1))
        }
    }

    /**
     * Evaluates stack elements.
     */
    def eval3(f: Function4[sequence.Vector[A], B, B, B, B]): Action[A] = new Action[A] {
        override def apply(v: sequence.Vector[A]) = {
            val e1 = delegate.pop
            val e2 = delegate.pop
            val e3 = delegate.pop
            delegate.push(f(v, e3, e2, e1))
        }
    }
}
