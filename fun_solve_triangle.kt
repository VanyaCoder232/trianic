import kotlin.math *


fun main(args: Array<String>) {
    println("Hello, World!")
}




fun Solve_Triangle(var a: Double, var b: Double, var c: Double,
				   var A: Double, var B: Double, var C: Double){
	A = math.radians(A)
	B = math.radians(B)
	C = math.radians(C)

	// сторона по т. косинусов
	if a ==0 and A>0 and b>0 and c>0:
		a=math.sqrt(b**2 + c**2 - 2*b*c*math.cos(A))
	if b ==0 and B>0 and a>0 and c>0:
		b=math.sqrt(a**2 + c**2 - 2*a*c*math.cos(B))
	if c ==0 and C>0 and a>0 and b>0:
		c=math.sqrt(a**2 + b**2 - 2*a*b*math.cos(C))

		// Углы по т. косинусов
	if A==0 and B==0 and C==0 and a>0 and b>0 and c>0: 
		A=math.acos( (b**2 + c**2 - a**2) / (2*b*c) )
		B=math.asin((b * math.sin(A)) / a)
		C=math.pi - A - B 
		return a, b, c, math.degrees(A), math.degrees(B), math.degrees(C)

	// Углы по т. синусов
	if a>0 and A==0:
		if b>0 and B>0:
			A=math.asin((a * math.sin(B)) / b)
		elif c>0 and C>0:
			A=math.asin((a * math.sin(C)) / c)
	if b>0 and B==0:
		if a>0 and A>0:
			B=math.asin((b * math.sin(A)) / a)
		elif c>0 and C>0:
			B=math.asin((b * math.sin(C)) / c)
	if c>0 and C==0:
		if a>0 and A>0:
			C=math.asin((a * math.sin(A)) / a)
		elif B>0 and b>0:
			C=math.asin((c * math.sin(B)) / b)

// Углы по т. суммы углов
	if A>0 and B>0 and C==0: 
		C=math.pi - A - B 
	elif A>0 and C>0 and B==0: 
		B=math.pi - A - C
	elif B>0 and C>0 and A==0: 
		A=math.pi - B - C

// стороны по т. синусов
	if a==0 and A>0:
		if b>0 and B>0:
			a=math.sin(A) * b / math.sin(B)
		elif c>0 and C>0:
			a=math.sin(A) * c / math.sin(C)

	if b==0 and B>0:
		if a>0 and A>0:
			b=math.sin(B) * a / math.sin(A)
		elif c>0 and C>0:
			b=math.sin(B) * c / math.sin(C)

	if c==0 and C>0:
		if a>0 and A>0:
			c=math.sin(C) * a / math.sin(A)

		elif b>0 and B>0:
			c=math.sin(C) * b / math.sin(B)

	return a, b, c, math.degrees(A), math.degrees(B), math.degrees(C)
}