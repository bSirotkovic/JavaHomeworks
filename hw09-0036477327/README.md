# 9. homework assignment; JAVA, Academic year 2016/2017; FER

Ovu domacu zadacu radite kao obican Eclipse Java projekt. Potrebne biblioteke koje se spominju smjestite u
direktorij lib unutar projekta (napravite ga), i zatim ih dodajte u _Build Path_.

## Problem 1.

Radimo vrlo jednostavnu biblioteku koja nudi potporu za rad s nekoliko razlicitih matematickih objekata:
vektori, kompleksni brojevi te polinomi.

Napravite paket hr.fer.zemris.math i u njega smjestite modele vektora, kompleksnih brojeva i polinoma.

Napravite razred Vector3 koji modelira neizmjenjiv trokomponentni vektor (sve operacije nad njime
vracaju nove objekte ovog tipa koji predstavljaju rezultat operacije). Javno sucelje ovog razreda
specificirano je u nastavku.

**public** Vector3( **double** x, **double** y, **double** z) {...} // konstruktor

**public double** norm() {...} // norma vektora (“duljina”)

**public** Vector3 normalized() {...} // normalizirani vektor

**public** Vector3 add(Vector3 other) {...} // zbrajanje

**public** Vector3 sub(Vector3 other) {...} // oduzimanje: ja minus drugi

**public double** dot(Vector3 other) {...} // skalarni produkt

**public** Vector3 cross(Vector3 other) {...} // vektorski produkt: ja i on

**public** Vector3 scale( **double** s) {...} // skaliranje zadanim faktorom

**public double** cosAngle(Vector3 other) {...} // kosinus kuta izmedu mene i njega

**public double** getX() {...} // prva komponenta vektora

**public double** getY() {...} // druga komponenta vektora

**public double** getZ() {...} // treca komponenta vektora

**public double** [] toArray() {...} // pretvorba u polje s 3 elementa

**public** String toString() {...} // pretvorba u string


Primjer ispitnog programa:

**public static void** main(String[] args) {
Vector3 i = **new** Vector3(1,0,0);
Vector3 j = **new** Vector3(0,1,0);
Vector3 k = i.cross(j);

```
Vector3 l = k.add(j).scale(5);
```
```
Vector3 m = l.normalized();
```
System. **_out_** .println(i);
System. **_out_** .println(j);
System. **_out_** .println(k);
System. **_out_** .println(l);
System. **_out_** .println(l.norm());
System. **_out_** .println(m);
System. **_out_** .println(l.dot(j));
System. **_out_** .println(i.add( **new** Vector3(0,1,0)).cosAngle(l));
}

Ocekivani ispis:

(1.000000, 0.000000, 0.000000)
(0.000000, 1.000000, 0.000000)
(0.000000, 0.000000, 1.000000)
(0.000000, 5.000000, 5.000000)
7.
(0.000000, 0.707107, 0.707107)
5.
0.

U nastavku cemo napraviti još model kompleksnog broja, te dva modela polinoma koji su zadani nad
kompleksnim brojevima, i ciji su koeficijenti kompleksni brojevi. Sva tri modela moraju stvarati
neizmjenjive objekte.


Napravite razred Complex koji modelira kompleksni broj, prema predlošku u nastavku.

```
public class Complex {
```
```
...
```
```
public static final Complex ZERO = new Complex(0,0);
public static final Complex ONE = new Complex(1,0);
public static final Complex ONE_NEG = new Complex(-1,0);
public static final Complex IM = new Complex(0,1);
public static final Complex IM_NEG = new Complex(0,-1);
```
```
public Complex() {...}
```
```
public Complex( double re, double im) {...}
```
```
// returns module of complex number
public double module() {...}
```
```
// returns this*c
public Complex multiply(Complex c) {...}
```
```
// returns this/c
public Complex divide(Complex c) {...}
```
```
// returns this+c
public Complex add(Complex c) {...}
```
```
// returns this-c
public Complex sub(Complex c) {...}
```
```
// returns -this
public Complex negate() {...}
```
```
// returns this^n, n is non-negative integer
public Complex power( int n) {...}
```
```
// returns n-th root of this, n is positive integer
public List<Complex> root( int n) {...}
```
```
@Override
public String toString() {...}
}
```

Napravite razred ComplexRootedPolynomial koji modelira polinom nad kompleksim brojevima, prema
predlošku u nastavku. Radi se o polinomu _f(z)_ oblika _(z-z1)*(z-z2)*...*(z-zn)_ , gdje su _z1_ do _zn_ njegove
nultocke (a njih zadaje korisnik kroz konstruktor). Primjetite, radi se o polinomu _n_ -tog stupnja (kada biste
izmnožili zagrade). Sve nultocke zadaju se kao kompleksni brojevi, a i sam _z_ je kompleksan broj. Metoda
_apply_ prima neki konkretan _z_ i racuna koju vrijednost ima polinom u toj tocki.

**public class** ComplexRootedPolynomial {

```
// ...
```
```
// constructor
public ComplexRootedPolynomial(Complex ...roots) {...}
```
```
// computes polynomial value at given point z
public Complex apply(Complex z) {...}
```
```
// converts this representation to ComplexPolynomial type
public ComplexPolynomial toComplexPolynom() {...}
```
```
@Override
public String toString() {...}
```
// finds index of closest root for given complex number z that is within
// treshold; if there is no such root, returns -
**public int** indexOfClosestRootFor(Complex z, **double** treshold) {...}
}

Napravite razred ComplexPolynomial koji modelira polinom nad kompleksim brojevima, prema predlošku
u nastavku. Radi se o polinomu _f(z)_ oblika _zn*zn+zn-1*zn-1+...+z 2 *z_^2 _+z 1 *z+z 0_ , gdje su _z0_ do _zn_ koeficijenti
koji pišu uz odgovarajuce potencije od _z_ (i zadaje ih korisnik kroz konstruktor). Primjetite, radi se o
polinomu _n_ -tog stupnja (što još zovemo red – engl. _polinom order_ ). Svi koeficijenti zadaju se kao
kompleksni brojevi, a i sam _z_ je kompleksan broj. Metoda _apply_ prima neki konkretan _z_ i racuna koju
vrijednost ima polinom u toj tocki.

**public class** ComplexPolynomial {

```
// ...
```
```
// constructor
public ComplexPolynomial(Complex ...factors) {...}
```
```
// returns order of this polynom; eg. For (7+2i)z^3+2z^2+5z+1 returns 3
public short order() {...}
```
```
// computes a new polynomial this*p
public ComplexPolynomial multiply(ComplexPolynomial p) {...}
```
```
// computes first derivative of this polynomial; for example, for
// (7+2i)z^3+2z^2+5z+1 returns (21+6i)z^2+4z+
public ComplexPolynomial derive() {...}
```
```
// computes polynomial value at given point z
public Complex apply(Complex z) {...}
```
@Override
**public** String toString() {...}
}


## Problem 2.

We will consider another kind of fractal images: fractals derived from Newton-Raphson iteration. As you
are surely aware, for about three-hundred years we know that each function that is _k_ -times differentiable
around a given point _x 0_ can be approximated by a _k_ -th order Taylor-polynomial:

```
f ( x 0 +e)= f ( x 0 )+ f' ( x 0 )e+
```
### 1

### 2!

```
f'' ( x 0 )e^2 +
```
### 1

### 3!

```
f''' ( x 0 )e^3 +...
```
So let _x 1_ be that point somewhere around the _x 0_ :

```
x 1 = x 0 +e
```
Substituting it into previously given formula we obtain:

```
f ( x 1 )= f ( x 0 )+ f' ( x 0 )( x 1 - x 0 )+
```
### 1

### 2!

```
f'' ( x 0 )( x 1 - x 0 )^2 +
```
### 1

### 3!

```
f''' ( x 0 )( x 1 - x 0 )^3 +...
```
For approximation of function _f_ we will restrict our self on linear approximation, so we can write:

```
f ( x 1 )˜ f ( x 0 )+ f' ( x 0 )( x 1 - x 0 )
```
Now, let us assume that we are interested in finding _x 1_ for which our function is equal to zero, i.e. we are
looking for _x 1_ for which _f(x 1 )=0_. Plugging this into above approximation, we obtain:

```
0 = f ( x 0 )+ f' ( x 0 )( x 1 - x 0 )
```
and from there:

```
x 1 = x 0 -
```
```
f ( x 0 )
f' ( x 0 )
```
However, since we used the approximation of _f_ , it is quite possible that _f(x 1 )_ is not actually equal to zero;
however, we hope that _f(x 1 )_ will be closer to zero than it was _f(x 0 )_. So, if that is true, we can iteratively apply
this expression to obtain better and better values for _x_ for which _f(x)=0_. So, we will use iterative expression:

```
xn + 1 = xn -
```
```
f ( xn )
f' ( xn )
```
which is known as Newton-Raphson iteration.

For this homework we will consider complex polynomial functions. For example, lets consider the complex
polynomial whose roots are +1, -1, _i_ and _-i_ :

```
f ( z )=( z - 1 )( z + 1 )( z - i )( z + i )= z^4 - 1
```
After deriving we obtain:

```
f' ( z )=4z^3
```

It is easy to see that our function _f_ becomes 0 for four distinct complex numbers _z_. However, we will
pretend that we don't know those roots. Instead, we will start from some initial complex point _c_ and plug it
into our iterative expression:

```
zn + 1 = zn -
```
```
f ( zn )
f' ( zn )
```
```
= xn -
z^4 - 1
4z^3
```
```
with z 0 = c.
```
We will generate iterations until we reach a predefined number of iterations (for example 16) or until
module | _zn+1-zn_ | becomes adequately small (for example, convergence threshold 1E-3). Once stopped, we
will find the closest function root for final point _zn_ , and color the point _c_ based on index of that root (let root
indexes start from 1). However, if we stopped on a _zn_ that is further than predefined threshold from all roots,
we will color the point _c_ with a color associated with index 0.

For example, if the function roots are +1, -1, _i_ and - _i_ , if acceptable root-distance is 0.002, if convergence
threshold equals 0.001 and if we stopped iterating after _z 7_ =-0.9995+ _i_ 0 because z7 was closer to _z 6_ =-
0.9991+ _i_ 0 then convergence threshold, we will determine that _z 7_ is closest to second function root (first is
+1, second is -1, third is +i, fourth is -i) and that _z 7_ is within predetermined root-distance (0.002) to -1, so we
will color pixel _c_ based on color associated with index 2.

We will proceed just as with Mandelbrot fractal:

for(y in ymin to ymax) {
for(x in xmin to xmax) {
c = map_to_complex_plain(x, y, xmin, xmax, ymin, ymax, remin, remax, immin, immax);
zn = c;
iter = 0;
iterate {
zn1 = zn – f(zn)/f'(zn);
iter++;
} while(|zn1-zn|>convergenceTreshold && iter<maxIter);
index = findClosestRootIndex(zn1, rootTreshold);
if(index==-1) { data[offset++]=0; } else { data[offset++]=index; }
}
}

We use _data[]_ array same way as we did for Mandelbrot fractal and the GUI component will handle the rest;
the only difference here is that content of _data[]_ array does not represent the speed of divergence but instead
holds the indexes of roots in which observed complex point _c_ has converged or 0 if no convergence to a root
occurred. Another difference is that the upper limit to data[i] is number of roots, so we won't call observer
with:

observer.acceptResult(data, ( **short** )(m), requestNo);

but instead with:

observer.acceptResult(data, ( **short** )(polynom.order()+1), requestNo);

If you completed this correct, for our first example with roots +1, -1, +i and -i you will get the following
picture:


In order to solve this, you will have to download from Ferko repository fractal-viewer-1.0.jar and
include it in _build-path_ (save it into lib folder; _javadoc_ is included as separate jar).

More verbose introduction to fractals based on Newton-Raphson iteration can be found at:
[http://www.chiark.greenend.org.uk/~sgtatham/newton/](http://www.chiark.greenend.org.uk/~sgtatham/newton/)

**Details**

Given the classes you developed in problem 1, the core of iteration loop can be written as:

```
Complex numerator = polynomial.apply(zn);
Complex denominator = derived.apply(zn);
Complex fraction = numerator.divide(denominator);
Complex zn1 = zn.sub(fraction);
module = zn1.sub(zn).module();
zn = zn1;
```

Write a main program hr.fer.zemris.java.fractals.Newton. The program must ask user to enter roots
as given below (observe the syntax used), and then it must start fractal viewer and display the fractal. In
order to run this successfully, you will have to add classpath configuration argument in command line when
starting java.

C:\somepath> java hr.fer.zemris.java.fractals.Newton
Welcome to Newton-Raphson iteration-based fractal viewer.
Please enter at least two roots, one root per line. Enter 'done' when done.
Root 1> 1
Root 2> -1 + i
Root 3> i
Root 4> 0 - i
Root 5> done
Image of fractal will appear shortly. Thank you.

(user inputs are shown in red)

General syntax for complex numbers is of form _a+ib_ or _a-ib_ where parts that are zero can be dropped, but
not both (empty string is not legal complex number); for example, zero can be given as 0, i0, 0+i0, 0-i0. If
there is ' _i_ ' present but no _b_ is given, you must assume that _b=_.

The implementation of IFractalProducer that you will supply must use parallelization to speed up the
rendering. The range of _y_ -s must be divided into 8 * numberOfAvailableProcessors jobs. For running
your jobs you must use ExecutorService based on FixedThreadPool, and you must collect your jobs by
calling get() on provided Future objects. Do not create new ExecutorService for each call of method
produce. Instead, create it in producer's constructor. Use a variant of FixedThreadPool which allows you
to specify a custom ThreadFactory as last argument. Implement a DaemonicThreadFactory that produces
threads which have daemon flag set to true and pass an instance of this factory to the
newFixedThreadPool; this way, your program won't hang once the GUI is closed.


## Problem 3.

You will write a simplification of a ray-tracer for rendering of 3D scenes; don't worry – it's easy and fun.
And also, we won't write a full-blown ray-tracer but only a ray-caster.

Please download from Ferko repository raytracer-1.0.jar; sources are available as separate jar. Save
both jars in lib directory, add raytracer-1.0.jar to _build-path_ and set raytracer-1.0-sources.jar as
raytracer-1.0.jar's sources file (right click, properties, ...). **Do not unpack raytracer-1.0-sources.jar**
into your project. To better understand the needed theory, you are advised to download the book available at:

[http://java.zemris.fer.hr/nastava/irg/](http://java.zemris.fer.hr/nastava/irg/)

(version knjiga-0.1.2016-03-02.pdf) and read section 9.2 (Phong model, pages 231 to 236) and section 10.
(Ray-casting algorithm, pages 241 to 244). To render an image using ray-casting algorithm, you start by
defining which object are present in the 3D scene, where are you stationed (eye-position: O), where do you
look at (view position: G) and in which direction is “up” (view-up approximation). See next image.

Now imagine that you have constructed a plane perpendicular to vector that connects the eye position (O)
and the view point (G). In that plane you will create a 2D coordinate system, so you will have the x-axis (as
indicated by vector _i_ on the image) and the y-axis (as indicated by vector _j_ on the image). If you only start
with an eye-position and a view point, your y-axis can be arbitrarily placed in this plane (you could rotate it
for any angle). To help us fix the direction of the y-axis, it is customary to specify another vector: the _view-
up_ vector which does not have to lay in the plane but it also must not be co-linear with G-O vector, so that a
projection of this vector onto the plane exists. If this is true, then take a look at the projection of the view-up
vector into the plane: we will use the normalized version of this projection to become our _j_ vector and hence
determine the orientation of y-axis.

Lets start calculating. Let: _OG_ ? =

### G ?- O ?

### ? G ?- O ??

```
, i.e. it is the normalized vector from O ? to G ? ; let
```
```
VUV ? be normalized version of the view-up vector. Then we can obtain the ? j' vector as follows:
```

```
? j' = VUV ? - OG ? ( OG ?· VUV ? ) where OG ?· VUV ? is a scalar product. Define its normalized version to be:
```
```
? j =
? j'
?? j' ?
```
. Now we can calculate vector ? _i'_ which will determine the orientation of the x-axis as a cross

product: ? _i'_ = _OG_ ? ×? _j_ and its normalized version ? _i_ =

```
? i'
?? i' ?
```
### .

Once we have determined where the plane is and what are the vectors determining our x-asis (i.e. ? _i_ ) and
y-axis (i.e. ? _j_ ), we have to decide which part of this plane will be mapped to our screen so that we can
determine where inthis plaine each screen-pixel is located (where is position (0,0), (0,1), etc.). We will
assume it to be a rectangle going left from _G_ ? (i.e. in direction -? _i_ ) for _l_ , going right from _G_ ? (i.e. in
direction ? _i_ ) for _r_ , going up from _G_ ? (i.e. in direction ? _j_ ) for _t_ , and finally going down from _G_ ?

(i.e. in direction -? _j_ ) for _b_. To simplify things further, lets assume that _l_ = _r_ =
_horizontal_
2

```
and
```
```
t = b =
vertical
2
```
```
where we introduced two parameters: horizontal and vertical.
```
In provided libraries I have already prepared the class Point3D with implemented methods for calculation
of scalar products, cross-products, vector normalization etc. so use it.

Now we will define final screen coordinate system, as shown in the next image.

We will define (0,0) to be the upper left point of our rectangular part of the plane; the x-axis will be oriented
just as ? _i_ vector is, and the y-axis will be oriented opposite from ? _j_ vector. We can obtain the 3D
coordinates of our upper-left corner as follows:

```
corner ? = G ?-
horizontal
2
```
```
·? i +
vertical
2
```
```
·? j
```
Now for each _x_ from 0 to _w_ -1 and for each _y_ from 0 to _h_ -1 we can calculate the 3D position of the screen-
pixel ( _x_ , _y_ ) in the plane as follows:

```
point ? xy = corner ? + x
w - 1
```
```
· horizontal ·? i - y
h - 1
```
```
· vertical ·? j
```
And now it is simple: we define a ray of light which starts at _O_ ? and passes through _point_ ? _xy_ .Then we


check if this ray which is specified by starting point _O_ ? and normalized directional vector

```
d ?=
```
```
point ? xy - O ?
? point ? xy - O ??
```
```
has any intersections with objects in scene! If an intersection is found, then that is
```
exactly what will determine the color of screen-pixel ( _x_ , _y_ ). If no intersection is found, the pixel will be
rendered black (r=g=b=0). However, if an intersection is found, we must determine the color of the pixel. If
multiple intersections are found, we must chose the closest one to eye-position since that is what the human
observer will see. For coloring we will use Phong’s model which assumes that there is one or more point-
light-sources present in scene. In our example there are two light sources (one green and one red in the
previous image). Each light source is specified with intensities of _r_ , _g_ and _b_ components it radiates.

Here is the pseudo code for the above described procedure:

for each pixel ( _x_ , _y_ )
calculate ray _r_ from eye-position to pixelxy
determine closest intersection _S_ of ray _r_ and any object in the scene (in front of observer)
if no _S_ exists, color ( _x_ , _y_ ) with rgb(0,0,0) else use rbg(determineColorFor( _S_ ))

The procedure determineColorFor( _S_ ) is given by the following pseudocode:

set color = rgb(15,15,15) // i.e. ambient component
for each light source ls
define ray r' from ls.position to S
find closest intersection S' of r' and any objects in scene
if S' exists and is closer to ls.position than S, skip this light source (it is obscured by that object!)
else color += diffuse component + reflective component

**Details**

Go through sources of IrayTracerProducer, IrayTracerResultObserver, GraphicalObject,
LightSource, Scene, Point3D, Ray and RayIntersection. Create package
hr.fer.zemris.java.raytracer.model in your homework and add class Sphere:

**package** hr.fer.zemris.java.raytracer.model;

**public class** Sphere **extends** GraphicalObject {

```
...
```
```
public Sphere(Point3D center, double radius, double kdr, double kdg,
double kdb, double krr, double krg, double krb, double krn) {
...
}
```
**public** RayIntersection findClosestRayIntersection(Ray ray) {
...
}
}

and implement all that is missing. Until you do that, the method which is used to build the default scene will
not work (RayTracerViewer. _createPredefinedScene_ ()). Coeficients _kd*_ determine the object
parameters for diffuse component and _kr*_ for reflective components.


Write a main program hr.fer.zemris.java.raytracer.RayCaster. The basic structure of the program
should look like this:

**public static void** main(String[] args) {
RayTracerViewer. _show_ ( _getIRayTracerProducer_ (),
**new** Point3D(10,0,0),
**new** Point3D(0,0,0),
**new** Point3D(0,0,10),
20, 20);
}

**private static** IRayTracerProducer getIRayTracerProducer() {
**return new** IRayTracerProducer() {

```
@Override
public void produce(Point3D eye, Point3D view, Point3D viewUp,
double horizontal, double vertical, int width, int height,
long requestNo, IRayTracerResultObserver observer) {
```
```
System. out .println("Zapocinjem izracune...");
short [] red = new short [width*height];
short [] green = new short [width*height];
short [] blue = new short [width*height];
```
```
Point3D zAxis = ...
Point3D yAxis = ...
Point3D xAxis = ...
```
```
Point3D screenCorner = ...
```
```
Scene scene = RayTracerViewer. createPredefinedScene ();
```
```
short [] rgb = new short [3];
int offset = 0;
for ( int y = 0; y < height; y++) {
for ( int x = 0; x < width; x++) {
Point3D screenPoint = ...
Ray ray = Ray. fromPoints (eye, screenPoint);
```
```
tracer (scene, ray, rgb);
```
```
red[offset] = rgb[0] > 255? 255 : rgb[0];
green[offset] = rgb[1] > 255? 255 : rgb[1];
blue[offset] = rgb[2] > 255? 255 : rgb[2];
```
```
offset++;
}
}
```
System. _out_ .println("Izracuni gotovi...");
observer.acceptResult(red, green, blue, requestNo);
System. _out_ .println("Dojava gotova...");
}
};
}

Fill the missing parts! If you do this OK, you will get the following image.


Now if this goes OK, please observe that calculation of color for each pixel is independent from other
pixels. Using this knowledge write a main program
hr.fer.zemris.java.raytracer.RayCasterParallel which parallelizes the calculation using Fork-Join
framework and RecursiveAction.


**Pomoc pri rješavanju ovog zadatka**

U nastavku je dan ispis izracunatih vrijednosti za nekoliko slucajeva. U metodi main gdje pozivate
RayTracerViewer.show(...) zadajete ocište, gledište te view-up vektor; pretpostavka je da su oni zadani kao
u uputi. Pri pozivu metode produce tada ce vrijediti:

Parametri koje je dobila metoda
===============================
eye: (10.000000, 0.000000, 0.000000)
view: (0.000000, 0.000000, 0.000000)
viewUp: (0.000000, 0.000000, 10.000000)
width: 500
height: 500
horizontal: 20.
vertical: 20.

Izracunato
===============================
X-vektor: (0.000000, 1.000000, -0.000000)
Y-vektor: (0.000000, 0.000000, 1.000000)
Z-vektor: (-1.000000, 0.000000, 0.000000)
Screen-corner: (0.000000, -10.000000, 10.000000)

Slijedi ispis zraka za odabrane tocke ekrana (uzeo sam x=0, w/3, w/2, 2w/3, w-1 “puta” y=0, h/3, h/2, 2h/3,
h-1):

Informacije za tocku x=0, y=
Screen-point: (0.000000, -10.000000, 10.000000)
Ray: start=(10.000000, 0.000000, 0.000000), direction=(-0.577350, -0.577350, 0.577350)
RGB =[0,0,0]

Informacije za tocku x=166, y=
Screen-point: (0.000000, -3.346693, 10.000000)
Ray: start=(10.000000, 0.000000, 0.000000), direction=(-0.688102, -0.230287, 0.688102)
RGB =[0,0,0]

Informacije za tocku x=250, y=
Screen-point: (0.000000, 0.020040, 10.000000)
Ray: start=(10.000000, 0.000000, 0.000000), direction=(-0.707106, 0.001417, 0.707106)
RGB =[0,0,0]

Informacije za tocku x=333, y=
Screen-point: (0.000000, 3.346693, 10.000000)
Ray: start=(10.000000, 0.000000, 0.000000), direction=(-0.688102, 0.230287, 0.688102)
RGB =[0,0,0]

Informacije za tocku x=499, y=
Screen-point: (0.000000, 10.000000, 10.000000)
Ray: start=(10.000000, 0.000000, 0.000000), direction=(-0.577350, 0.577350, 0.577350)
RGB =[0,0,0]

Informacije za tocku x=0, y=
Screen-point: (0.000000, -10.000000, 3.346693)
Ray: start=(10.000000, 0.000000, 0.000000), direction=(-0.688102, -0.688102, 0.230287)
RGB =[0,0,0]

Informacije za tocku x=166, y=
Screen-point: (0.000000, -3.346693, 3.346693)
Ray: start=(10.000000, 0.000000, 0.000000), direction=(-0.903874, -0.302499, 0.302499)
RGB =[78,123,123]


Informacije za tocku x=250, y=
Screen-point: (0.000000, 0.020040, 3.346693)
Ray: start=(10.000000, 0.000000, 0.000000), direction=(-0.948301, 0.001900, 0.317367)
RGB =[153,72,57]

Informacije za tocku x=333, y=
Screen-point: (0.000000, 3.346693, 3.346693)
Ray: start=(10.000000, 0.000000, 0.000000), direction=(-0.903874, 0.302499, 0.302499)
RGB =[0,0,0]

Informacije za tocku x=499, y=
Screen-point: (0.000000, 10.000000, 3.346693)
Ray: start=(10.000000, 0.000000, 0.000000), direction=(-0.688102, 0.688102, 0.230287)
RGB =[0,0,0]

Informacije za tocku x=0, y=
Screen-point: (0.000000, -10.000000, -0.020040)
Ray: start=(10.000000, 0.000000, 0.000000), direction=(-0.707106, -0.707106,
-0.001417)
RGB =[0,0,0]

Informacije za tocku x=166, y=
Screen-point: (0.000000, -3.346693, -0.020040)
Ray: start=(10.000000, 0.000000, 0.000000), direction=(-0.948301, -0.317367,
-0.001900)
RGB =[49,57,57]

Informacije za tocku x=250, y=
Screen-point: (0.000000, 0.020040, -0.020040)
Ray: start=(10.000000, 0.000000, 0.000000), direction=(-0.999996, 0.002004, -0.002004)
RGB =[76,33,33]

Informacije za tocku x=333, y=
Screen-point: (0.000000, 3.346693, -0.020040)
Ray: start=(10.000000, 0.000000, 0.000000), direction=(-0.948301, 0.317367, -0.001900)
RGB =[115,69,69]

Informacije za tocku x=499, y=
Screen-point: (0.000000, 10.000000, -0.020040)
Ray: start=(10.000000, 0.000000, 0.000000), direction=(-0.707106, 0.707106, -0.001417)
RGB =[0,0,0]

Informacije za tocku x=0, y=
Screen-point: (0.000000, -10.000000, -3.346693)
Ray: start=(10.000000, 0.000000, 0.000000), direction=(-0.688102, -0.688102,
-0.230287)
RGB =[0,0,0]

Informacije za tocku x=166, y=
Screen-point: (0.000000, -3.346693, -3.346693)
Ray: start=(10.000000, 0.000000, 0.000000), direction=(-0.903874, -0.302499,
-0.302499)
RGB =[0,0,0]

Informacije za tocku x=250, y=
Screen-point: (0.000000, 0.020040, -3.346693)
Ray: start=(10.000000, 0.000000, 0.000000), direction=(-0.948301, 0.001900, -0.317367)
RGB =[62,80,80]

Informacije za tocku x=333, y=
Screen-point: (0.000000, 3.346693, -3.346693)


Ray: start=(10.000000, 0.000000, 0.000000), direction=(-0.903874, 0.302499, -0.302499)
RGB =[92,61,61]

Informacije za tocku x=499, y=
Screen-point: (0.000000, 10.000000, -3.346693)
Ray: start=(10.000000, 0.000000, 0.000000), direction=(-0.688102, 0.688102, -0.230287)
RGB =[0,0,0]

Informacije za tocku x=0, y=
Screen-point: (0.000000, -10.000000, -10.000000)
Ray: start=(10.000000, 0.000000, 0.000000), direction=(-0.577350, -0.577350,
-0.577350)
RGB =[0,0,0]

Informacije za tocku x=166, y=
Screen-point: (0.000000, -3.346693, -10.000000)
Ray: start=(10.000000, 0.000000, 0.000000), direction=(-0.688102, -0.230287,
-0.688102)
RGB =[0,0,0]

Informacije za tocku x=250, y=
Screen-point: (0.000000, 0.020040, -10.000000)
Ray: start=(10.000000, 0.000000, 0.000000), direction=(-0.707106, 0.001417, -0.707106)
RGB =[0,0,0]

Informacije za tocku x=333, y=
Screen-point: (0.000000, 3.346693, -10.000000)
Ray: start=(10.000000, 0.000000, 0.000000), direction=(-0.688102, 0.230287, -0.688102)
RGB =[0,0,0]

Informacije za tocku x=499, y=
Screen-point: (0.000000, 10.000000, -10.000000)
Ray: start=(10.000000, 0.000000, 0.000000), direction=(-0.577350, 0.577350, -0.577350)
RGB =[0,0,0]

Najjednostavnija implementacija metode tracer je ona koja provjerava sijece li se zraka s bilo kojih
objektom. Ako da, piksel boja bijelo, inace ga ostavlja crno:

```
protected static void tracer(Scene scene , Ray ray, short [] rgb ) {
rgb[0] = 0;
rgb[1] = 0;
rgb[2] = 0;
```
```
RayIntersection closest = findClosestIntersection (scene, ray);
```
```
if (closest== null ) {
return ;
}
```
```
rgb[0] = 255;
rgb[1] = 255;
rgb[2] = 255;
}
```
Uz ovakvu implementaciju, dobit cete sliku kao u nastavku.


Jednom kad Vam to radi, krenite na stvarno bojanje.

Prilikom bojanja, za svaki izvor uzet cete zraku koja krece iz izvora i ide prema pronadenom sjecištu. Potom
cete pogledati sijece li se ta zraka s cime. Ocekivano je da je odgovor potvrdan: zraka se sijece barem sa
objektom prema kojem ste je usmjerili. Ako postoji sjecište s nekim bližim objektom, onda taj zaklanja
izvor i izvor se zanemaruje (nema doprinosa promatranoj tocki jer ne osvjetljava sjecište). Ovdje trebate
paziti samo na jedan sitan implementacijski detalj koji se može pojaviti zbog numerickih nepreciznosti pri
izracunu: kad usporedujete udaljenosti (tip double), uvijek uzmite u obzir odredene tolerancije.


**Please note.** You can consult with your peers and exchange ideas about this homework _before_ you start
actual coding. Once you open you IDE and start coding, consultations with others (except with me) will be
regarded as cheating. You can not use any of preexisting code or libraries for this homework (whether it is
yours old code or someones else). You can use Java Collection Framework and other parts of Java covered
by lectures; if unsure – e-mail me. Document your code!

**If you need any help, I have reserved a slot for consultations every day from Monday to Thursday at 1
PM with exception of Tuesday when the slot starts at 2 PM. Feel free to drop by my office.**

All source files must be written using UTF-8 encoding. All classes, methods and fields (public, private or
otherwise) must have appropriate javadoc.

When your **complete** homework is done, pack it in zip archive with name hw09-0000000000.zip (replace
zeros with your JMBAG). Upload this archive to Ferko before the deadline. Do not forget to lock your
upload or upload will not be accepted. Deadline is May 5th 2017. at 07:00 AM.

## You are expected to write tests for Vector3 and Complex

## in problem 1. In order to do so, go in your Eclipse

## project build-path, Add library, JUnit 4. Create new

## source folder (test) and place tests in it.

## You are encouraged to write tests for other problems.


