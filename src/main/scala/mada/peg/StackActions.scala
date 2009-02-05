

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


/**
 * Helper for stack evaluations.
 */
class StackActions[A, B](val stack: Stack[B]) {


// constructors

    /**
     * Constructs from an empty <code>stack</code>.
     */
    def this() = this(new java.util.ArrayDeque[B])


// apply

    /**
     * Alias of <code>eval0</code>
     */
    final def apply(f: Function1[Vector[A], B]): Peg.Action[A] = eval0(f)

    /**
     * Alias of <code>eval1</code>
     */
    final def apply(f: Function2[Vector[A], B, B]): Peg.Action[A] = eval1(f)

    /**
     * Alias of <code>eval2</code>
     */
    final def apply(f: Function3[Vector[A], B, B, B]): Peg.Action[A] = eval2(f)

    /**
     * Alias of <code>eval3</code>
     */
    final def apply(f: Function4[Vector[A], B, B, B, B]): Peg.Action[A] = eval3(f)


// eval

    /**
     * Pushes the result of applying region to <code>f</code>.
     */
    def eval0(f: Function1[Vector[A], B]): Peg.Action[A] = new Peg.Action[A] {
        override def apply(v: Vector[A]) = {
            stack.push(f(v))
        }
    }

    /**
     * Evaluates stack elements.
     */
    def eval1(f: Function2[Vector[A], B, B]): Peg.Action[A] = new Peg.Action[A] {
        override def apply(v: Vector[A]) = {
            val e1 = stack.pop
            stack.push(f(v, e1))
        }
    }

    /**
     * Evaluates stack elements.
     */
    def eval2(f: Function3[Vector[A], B, B, B]): Peg.Action[A] = new Peg.Action[A] {
        override def apply(v: Vector[A]) = {
            val e1 = stack.pop
            val e2 = stack.pop
            stack.push(f(v, e2, e1))
        }
    }

    /**
     * Evaluates stack elements.
     */
    def eval3(f: Function4[Vector[A], B, B, B, B]): Peg.Action[A] = new Peg.Action[A] {
        override def apply(v: Vector[A]) = {
            val e1 = stack.pop
            val e2 = stack.pop
            val e3 = stack.pop
            stack.push(f(v, e3, e2, e1))
        }
    }
}
