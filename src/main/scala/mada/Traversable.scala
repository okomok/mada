

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


package object traversable {

    @aliasOf("Traversable")
    type Type[+A] = Traversable[A]

}


/**
 * Yet another Iterable
 */
trait Traversable[+A] { ^ =>

    import traversable._


    /**
     * Returns a starting traverser.
     */
    def start: Traverser[A]

    /**
     * Is this traversable again?
     */
    def isRetraversable: Boolean


    def flattenImpl[B](_this: Traversable[Traversable[B]]): Traversable[B] = throw new Error

    def stringizeImpl(_this: Traversable[Char]): String = throw new Error

    def lazyImpl[B](_this: => Traversable[B]): Traversable[B] = throw new Error


// sorted

    /**
     * @return  <code>mergeby(that)(c)</code>.
     */
    final def merge[B >: A](that: Traversable[B])(implicit c: Compare[B]): Traversable[B] = mergeBy(that)(c)

    /**
     * Combines the elements tr the sorted traversables, into a new traversable with its elements sorted.
     */
    def mergeBy[B >: A](that: Traversable[B])(lt: Compare.Func[B]): Traversable[B] = bind(new MergeTraverser[B](start, that.start, lt))

}


@companionModule
object Traversable {

    sealed trait ByNameMethods[A] {
        def `lazy`: Traversable[A]
    }
    implicit def byNameMethods[A](tr: => Traversable[A]): ByNameMethods[A] = new ByNameMethods[A] {
        override def `lazy` = tr.lazyImpl(tr)
    }

    sealed trait TraversableMethods[A] {
        def flatten: Traversable[A]
    }
    implicit def traversableMethods[A](tr: Traversable[Traversable[A]]): TraversableMethods[A] = new TraversableMethods[A] {
        override def flatten = tr.flattenImpl(tr)
    }

    sealed trait CharMethods {
        def stringize: String
    }
    implicit def charMethods(tr: Traversable[Char]): CharMethods = new CharMethods {
        override def stringize = tr.stringizeImpl(tr)
    }

}
