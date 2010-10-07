

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


/**
 * Generates an element on demand.
 */
trait Generator[+A] {
    /**
     * Returns true iif no more elments generated.
     * `false` by default.
     */
    def isEmpty: Boolean = false

    /**
     * Generates one element.
     */
    def generate: Unit

    @equivalentTo("for (_ <- 0 until n) generate")
    def generateN(n: Int) = for (_ <- 0 until n) generate

    /**
     * Generates all the elements.
     */
    def generateAll: Unit = throw new UnsupportedOperationException("Generator.generateAll")

    /**
     * Retrieves a snapshot.
     */
    def sequence: Reactive[A]
}


object Generator {

    @returnThat
    def from[A](to: Generator[A]): Generator[A] = to

    implicit def fromIterative[A](from: Iterative[A]): Generator[A] = new FromIterative(from)
    implicit def fromIterativeSequence[A](from: iterative.Sequence[A]): Generator[A] = fromIterative(from.asIterative)
    implicit def fromArray[A](from: Array[A]): Generator[A] = fromIterative(from)
    implicit def fromOption[A](from: Option[A]): Generator[A] = fromIterative(from)
    implicit def fromSIterable[A](from: Iterable[A]): Generator[A] = fromIterative(from)
    implicit def fromJIterable[A](from: java.lang.Iterable[A]): Generator[A] = fromIterative(from)
    implicit def fromJObjectInput(from: java.io.ObjectInput): Generator[AnyRef] = fromIterative(from)
    implicit def fromJReader(from: java.io.Reader): Generator[Char] = fromIterative(from)
    // cf. https://lampsvn.epfl.ch/trac/scala/ticket/3152
    // implicit def fromIterativeLike[A, B](from: A)(implicit pre: A <%< iterative.Sequence[B]): Generator[B] = fromIterative(pre(from).asIterative)

    /**
     * Creates a Generator initially containing the specified elements.
     */
    object Of {
        def apply[A](from: A*): Generator[A] = fromIterative(from)
    }

    @aliasOf("Of.apply")
    def apply[A](from: A*): Generator[A] = fromIterative(from)

    /**
     * Helps to implement a trivial Generator.
     */
    abstract class Trivial[A] extends Generator[A] { self =>
        protected def generateTo(f: A => Unit): Unit
        protected def generateAllTo(fs: Iterative[A => Unit]): Unit = throw new UnsupportedOperationException("Generator.generateAll")

        private val outs = new java.util.concurrent.CopyOnWriteArrayList[A => Unit]

        final override def generate = iterative.from(outs).foreach(generateTo(_))
        final override def generateAll = generateAllTo(iterative.from(outs))

        final override def sequence: Reactive[A] = new Resource[A] {
            private var out: A => Unit = null
            override protected def closeResource = self.outs.remove(out)
            override protected def openResource(f: A => Unit) = {
                out = Reaction(f)
                self.outs.add(out)
            }
        }
    }

    private
    class FromIterative[A](_1: Iterative[A]) extends Trivial[A] {
        private val it = _1.begin
        private def _next(f: A => Unit) = {
            val x = ~it
            it.++
            f(x)
        }

        override def isEmpty = synchronized { it.isEnd }
        override protected def generateTo(f: A => Unit) = synchronized { if (!isEmpty) _next(f) }
        override protected def generateAllTo(fs: Iterative[A => Unit]) = {
            for (f <- fs) {
                synchronized { while (!isEmpty) _next(f) }
            }
        }
    }

    private
    case class Reaction[A](from: A => Unit) extends (A => Unit) {
        override def apply(x: A) = from(x)
    }

}
