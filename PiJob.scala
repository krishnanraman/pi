import com.twitter.scalding._

/* HAPPY PI DAY !!!
Good: π^2/8 = (1/1^2 + 1/3^2 + 1/5^2 + ...)
Better: π^4/96 = (1/1^4 + 1/3^4 + 1/5^4 + ...)
Best: π^6/960 = (1/1^6 + 1/3^6 + 1/5^6 + ...)

RESULTS FROM SCALDING:
$ more pi-good/part-00000 
3.1409572404677313
$ more pi-better/part-00000 
3.141592653461558
$ more pi-best/part-00000 
3.1415926535897922

The last result is accurate to 14 significant digits!

Proof of the first, using Fourier series:

Let f(x)
= -x  when -π < x < 0
= 0  when 0 < x < π

Write out the Fourier series for f(x)

f(x) = π/4 - (2/π)*[cos(x) + (1/9)cos(3x) + (1/25)cos(5x) ...] - [sin(x) - (1/2)sin(2x) + (1/3)sin(3x) ...]

Evaluate at x=0

0 = π/4 - 2/π * (1 + 1/9 + 1/25 +...)

so π^2/8 = (1 + 1/9 + 1/25 + ...)

*/
class PiJob(args:Args) extends Job(args) {

  def computePi(src:RichPipe, name:String, power:Int, quotient:Int) = {
    src.map('n->'n) {
      i:Int => (quotient + 0.0)/math.pow(i, power)
    }
    .groupAll{
      _.sum[Double]('n -> 'nsum)
    }
    .mapTo('nsum -> 'pi) {
      nsum:Double => math.pow(nsum, 1.0/power)
    }
    .write(Tsv(name))
  }

  val src = IterableSource((1 to 1001 by 2), 'n)
  computePi(src, "pi-good",   2, 8)
  computePi(src, "pi-better", 4, 96)
  computePi(src, "pi-best",   6, 960)
}
