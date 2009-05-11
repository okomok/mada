

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


/**
 * Contains combinator birds.
 */
package object bird {

    /**
     * S combinator
     */
    def S[X, Y, Z](x: Z => Y => X)(y: Z => Y)(z: Z): X = x(z)(y(z))

    /**
     * K combinator
     */
    def K[X, Y](x: X)(y: Y): X = x

    /**
     * I combinator
     */
    def I[X](x: X): X = x

}
