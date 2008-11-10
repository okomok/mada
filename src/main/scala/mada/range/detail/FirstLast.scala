
package mada.range.detail


object First {
    def apply[A](r: Range[A]): A = {
        Assert("out of Range", !r.isEmpty)
        *(r.begin)
    }
}

object Last {
    def apply[A](r: Range[A]): A = {
        Assert("out of Range", !r.isEmpty)
        Assert("requires BidirectionalRange", r models BidirectionalTraversal)
        *(--(r.end))
    }
}
