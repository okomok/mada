

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


package hiter {

    object FoldLeft {
        def apply[B](it: HeteroIterator, z: B, op: (B, Any) => B): B = {
            if (it.hasNext) {
                apply(it.toNext, op(z, it.head), op)
            } else {
                z
            }
        }
    }

}

object HeteroIterable {

    val nullIterator: HeteroIterator = new HeteroIterator {
        override type Head = Nothing
        override type ToNext = Nothing

        override def head = throw new Error
        override def hasNext = false
        override def toNext = throw new Error
    }


    implicit def fromProduct1[T1, T2](from: Product2[T1, T2]): HeteroIterable = new HeteroIterable {
        type Elements = Product2HeteroIterator1.type

        override def elements = Product2HeteroIterator1

        object Product2HeteroIterator1 extends HeteroIterator {
            override type Head = T1
            override type ToNext = Product2HeteroIterator2.type

            override def head = from.productElement(0).asInstanceOf[Head]
            override def hasNext = true
            override def toNext = Product2HeteroIterator2
        }

        object Product2HeteroIterator2 extends HeteroIterator {
            override type Head = T2
            override type ToNext = nullIterator.type

            override def head = from.productElement(1).asInstanceOf[Head]
            override def hasNext = true
            override def toNext = nullIterator
        }
    }

    /**
     * Triggers implicit conversions explicitly.
     *
     * @return  <code>to</code>.
     */
    def from(to: HeteroIterable) = to
}

trait HeteroIterator {
    type Head
    type ToNext <: HeteroIterator

    def head: Head // google calls this `peek`.
    def hasNext: Boolean
    def toNext: ToNext
}


trait HeteroIterable {
    type Elements <: HeteroIterator

    def elements: Elements

    def foldLeft[B](z: B)(op: (B, Any) => B): B = hiter.FoldLeft(elements, z, op)
}
