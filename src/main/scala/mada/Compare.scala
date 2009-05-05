

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


import cmp._


/**
 * Contains utility methods operating on <code>Compare</code>.
 */
object Compare extends Aliases with Conversions with Compatibles {
    /**
     * Triggers implicit conversions explicitly.
     *
     * @return  <code>to</code>.
     */
    def from[A](to: Compare[A]) = to

    /**
     * Alias of <code>fromFunc</code>
     */
    def by[A](p: Func[A]): Compare[A] = fromFunc(p)
}


/**
 * Represents strict weak ordering.
 */
trait Compare[-A] extends Compare.Func[A] with Companion[Compare.type] {

    override def companion = Compare

    /**
     * @return  <code>true</code> iif x precedes y.
     */
    def apply(x: A, y: A): Boolean

    /**
     * @return  <code>if (apply(x, y)) -1 else if (apply(y, x)) 1 else 0</code>.
     */
    def threeWay(x: A, y: A): Int = if (apply(x, y)) -1 else if (apply(y, x)) 1 else 0
}
