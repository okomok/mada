
package mada.rng


object Compatibles extends Compatibles; trait Compatibles extends Namespace
        with ArrayCompatible
        with CellCompatible
        with IntervalCompatible
        with IteratorCompatible
        with ListCompatible
        with RandomAccessSeqCompatible
        with StreamCompatible
        with StringCompatible
        with jcl.Compatibles
