pi
==
```
Compute Pi via Fourier Series using Scalding

HAPPY PI DAY !!!
Good: π^2/8 = (1/1^2 + 1/3^2 + 1/5^2 + ...)
Better: π^4/96 = (1/1^4 + 1/3^4 + 1/5^4 + ...)
Best: π^6/960 = (1/1^6 + 1/3^6 + 1/5^6 + ...)

Proof of the first, using Fourier series:

Let f(x)
= -x  when -π < x < 0
= 0  when 0 < x < π

Write out the Fourier series for f(x)

f(x) = π/4 - (2/π)*[cos(x) + (1/9)cos(3x) + (1/25)cos(5x) ...] - [sin(x) - (1/2)sin(2x) + (1/3)sin(3x) ...]

Evaluate at x=0

0 = π/4 - 2/π * (1 + 1/9 + 1/25 +...)

so π^2/8 = (1 + 1/9 + 1/25 + ...)
```
