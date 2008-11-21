
package mada.rng


object DefaultCompatibles extends DefaultCompatibles

trait DefaultCompatibles extends Traits
        with ArrayCompatible
        with CellCompatible
        with IntervalCompatible
        with IteratorCompatible
        with JclCompatible
        with StringCompatible


object Compatibles extends Compatibles

trait Compatibles extends Traits
        with DefaultCompatibles
