package threads

object Main extends App {
	val t = new Threads()
	val a = Array(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
	val f = (x: Int) => x * x
	val b = t.parallelMap(f, a)
	for (x <- b) {
		print(x)
		print(" ")
	}
}
