

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


import traversable._


/**
 * Yet another Iterable
 */
trait Traversable[+A] { ^ =>

    /**
     * Returns a starting traverser.
     */
    def start: Traverser[A]

    /**
     * Is this traversable as many times as one wants?
     */
    def isRetraversable: Boolean


    def _flatten[B](_this: Traversable[Traversable[B]]): Traversable[B] = throw new Error

    def _stringize(_this: Traversable[Char]): String = throw new Error

    def _lazy[B](_this: => Traversable[B]): Traversable[B] = throw new Error


// sorted

    /**
     * @return  <code>mergeby(that)(c)</code>.
     */
    final def merge[B >: A](that: Traversable[B])(implicit c: Compare[B]) = mergeBy(that)(c)

    /**
     * Combines the elements tr the sorted traversables, into a new traversable with its elements sorted.
     */
    def mergeBy[B >: A](that: Traversable[B])(lt: Compare.Func[B]): Traversable[B] = bind(new MergeTraverser[B](start, that.start, lt))

}


object Traversable {

    sealed trait ByNameMethods[A] {
        def `lazy`: Traversable[A]
    }
    implicit def byNameMethods[A](tr: => Traversable[A]): ByNameMethods[A] = new ByNameMethods[A] {
        override def `lazy` = tr._lazy(tr)
    }

    sealed trait TraversableMethods[A] {
        def flatten: Traversable[A]
    }
    implicit def traversableMethods[A](tr: Traversable[Traversable[A]]): TraversableMethods[A] = new TraversableMethods[A] {
        override def flatten = tr._flatten(tr)
    }

    sealed trait CharMethods {
        def stringize: String
    }
    implicit def charMethods(tr: Traversable[Char]): CharMethods = new CharMethods {
        override def stringize = tr._stringize(tr)
    }

}
