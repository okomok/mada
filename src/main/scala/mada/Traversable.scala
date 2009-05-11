

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

    sealed trait OfByName[A] {
        def `lazy`: Traversable[A]
    }
    implicit def ofByname[A](tr: => Traversable[A]): OfByName[A] = new OfByName[A] {
        override def `lazy` = tr._lazy(tr)
    }

    sealed trait OfTraversable[A] {
        def flatten: Traversable[A]
    }
    implicit def ofTraversable[A](tr: Traversable[Traversable[A]]): OfTraversable[A] = new OfTraversable[A] {
        override def flatten = tr._flatten(tr)
    }

    sealed trait OfChar {
        def stringize: String
    }
    implicit def ofChar(tr: Traversable[Char]): OfChar = new OfChar {
        override def stringize = tr._stringize(tr)
    }

}
