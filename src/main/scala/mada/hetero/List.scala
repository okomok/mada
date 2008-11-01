package mada.hetero



object List {
	object Nil
	type Nil = Nil.type

	case class Cons[Car, Cdr](car: Car, cdr: Cdr) {
	}

	type List0 = Nil
	type List1[T1] = Cons[T1, List0]
	type List2[T1, T2] = Cons[T1, List1[T2]]
	type List3[T1, T2, T3] = Cons[T1, List2[T2, T3]]
	type List4[T1, T2, T3, T4] = Cons[T1, List3[T2, T3, T4]]
	type List5[T1, T2, T3, T4, T5] = Cons[T1, List4[T2, T3, T4, T5]]

	def apply: List0 = Nil
	def apply[T1](t1: T1): List1[T1] = new Cons(t1, apply)
	def apply[T1, T2](t1: T1, t2: T2): List2[T1, T2] = new Cons(t1, apply(t2))
	def apply[T1, T2, T3](t1: T1, t2: T2, t3: T3): List3[T1, T2, T3] = new Cons(t1, apply(t2, t3))
	def apply[T1, T2, T3, T4](t1: T1, t2: T2, t3: T3, t4: T4): List4[T1, T2, T3, T4] = new Cons(t1, apply(t2, t3, t4))
	def apply[T1, T2, T3, T4, T5](t1: T1, t2: T2, t3: T3, t4: T4, t5: T5): List5[T1, T2, T3, T4, T5] = new Cons(t1, apply(t2, t3, t4, t5))

	type Car1[T1, Cdr] = Cons[T1, Cdr]
	type Car2[T1, T2, Cdr] = Cons[T1, Car1[T2, Cdr]]
	type Car3[T1, T2, T3, Cdr] = Cons[T1, Car2[T2, T3, Cdr]]
	type Car4[T1, T2, T3, T4, Cdr] = Cons[T1, Car3[T2, T3, T4, Cdr]]
	type Car5[T1, T2, T3, T4, T5, Cdr] = Cons[T1, Car4[T2, T3, T4, T5, Cdr]]

	def _1[T1, Cdr](l: Car1[T1, Cdr]): T1 = l.car
	def _2[T1, T2, Cdr](l: Car2[T1, T2, Cdr]): T2 = l.cdr.car
	def _3[T1, T2, T3, Cdr](l: Car3[T1, T2, T3, Cdr]): T3 = l.cdr.cdr.car
	def _4[T1, T2, T3, T4, Cdr](l: Car4[T1, T2, T3, T4, Cdr]): T4 = l.cdr.cdr.cdr.car
	def _5[T1, T2, T3, T4, T5, Cdr](l: Car5[T1, T2, T3, T4, T5, Cdr]): T5 = l.cdr.cdr.cdr.cdr.car
}
