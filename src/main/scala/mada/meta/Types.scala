

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.meta


/**
 * Contains types.
 */
trait Types { this: Meta.type =>

    type int = boxed[scala.Int]
    type string = boxed[String]

}
