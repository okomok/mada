
package mada.rng


object ToRng extends ToRng

trait ToRng
        extends ArrayToRng
        with IteratorToRng
        with JclToRng
        with StringToRng
