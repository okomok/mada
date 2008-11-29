
package mada.rng


object DefaultCompatibles extends DefaultCompatibles; trait DefaultCompatibles extends Namespace
        with ArrayCompatible
        with CellCompatible
        with IntervalCompatible
        with IteratorCompatible
        with JclCompatible
        with ListCompatible
        with StringCompatible


object Compatibles extends Compatibles; trait Compatibles extends Namespace
        with DefaultCompatibles
