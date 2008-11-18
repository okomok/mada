
package mada.rng


object ToRng extends ToRng

trait ToRng
        extends ArrayToRng
        with IntervalToRng
        with IteratorToRng
        with JclToRng
        with SingleToRng
        with StringToRng
