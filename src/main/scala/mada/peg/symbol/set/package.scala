

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg.symbol


package object set {

    /**
     * @return  <code>fromIterable(vs)(c)</code>.
     */
    def Of[A](vs: Vector[A]*)(implicit c: Compare[A]): Set[A] = fromIterable(vs)(c)

    /**
     * Constructs <code>Set</code> containing <code>vs</code> as elements.
     */
    def fromIterable[A](vs: Iterable[Vector[A]])(lt: compare.Func[A]): Set[A] = {
        val r = Default(lt)
        for (v <- vs.view) {
            r += v
        }
        r
    }

}
