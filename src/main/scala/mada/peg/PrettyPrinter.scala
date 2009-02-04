

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


/**
 * Contains utility methods operating on type <code>PrettyPrinter</code>.
 */
object PrettyPrinter {
    /**
     * Returns a <code>PrettyPrinter</code> which outputs XML.
     * You must call <code>close</code>.
     */
    def xml: PrettyPrinter = new XMLPrettyPrinter

    /**
     * Returns a <code>PrettyPrinter</code> which outputs nothing.
     */
    val trash: PrettyPrinter = new PrettyPrinter {
        override def close = ()
        override def print[A](p: Peg[A]) = p
    }
}

/**
 * Prints matched input information.
 */
trait PrettyPrinter {
    /**
     * Closes output resource.
     */
    def close: Unit

    /**
     * Outputs matched inputs.
     */
    def print[A](p: Peg[A]): Peg[A]

    /**
     * @return  <code>print(p.named(name))</code>.
     */
    final def print[A](name: String)(p: Peg[A]): Peg[A] = print(p.named(name))

    /**
     * Alias of <code>print</code>
     */
    final def apply[A](p: Peg[A]): Peg[A] = print(p)

    /**
     * Alias of <code>print</code>
     */
    final def apply[A](name: String)(p: Peg[A]): Peg[A] = print(name)(p)
}
