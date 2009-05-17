

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


import compare._


/**
 * Represents strict weak ordering.
 */
trait Compare[-A] extends Func[A] {

    /**
     * @return  <code>true</code> iif x precedes y.
     */
    def apply(x: A, y: A): Boolean

    /**
     * @return  <code>if (apply(x, y)) -1 else if (apply(y, x)) 1 else 0</code>.
     */
    def threeWay(x: A, y: A): Int = if (apply(x, y)) -1 else if (apply(y, x)) 1 else 0

}


object Compare extends compare.Compatibles
