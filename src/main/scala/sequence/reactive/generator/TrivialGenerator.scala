

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive; package generator


/**
 * Helps to build a trivial Generator.
 */
abstract class TrivialGenerator[A] extends Generator[A] { self =>
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
