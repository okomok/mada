
package mada.rng


object ToRng extends ToRng; trait ToRng extends Namespace
        with ArrayToRng
        with CellToRng
        with IntervalToRng
        with IteratorToRng
        with ListToRng
        with StreamToRng
        with StringToRng
        with jcl.ToRng
