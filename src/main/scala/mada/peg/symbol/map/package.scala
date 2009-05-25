

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg.symbol


package object map {

    /**
     * @return  <code>fromIterable(es)(c)</code>.
     */
    def Of[A](es: (Vector[A], Peg[A])*)(implicit c: Compare[A]): Map[A] = fromIterable(es)(c)

    /**
     * Constructs <code>SymbolMet</code> containing <code>es</code> as key-and-value entries.
     */
    def fromIterable[A](es: Iterable[(Vector[A], Peg[A])])(lt: compare.Func[A]): Map[A] = {
        val r = Default(lt)
        for (e <- es.view) {
            r += Tuple2(e._1, e._2)
        }
        r
    }

}
