

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


/**
 * Generates an element on demand.
 */
trait Generator[+A] {
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

    /**
     * Mixin to build a trivial Generator.
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
    case class Reaction[A](from: A => Unit) extends (A => Unit) {
        override def apply(x: A) = from(x)
    }

}



