#ifndef _SHAPES_H_
#define _SHAPES_H_

#define PI 3.1415926

// Base class
class Shape {
public:
	Shape(double a = 0, double b = 0) {
		width = a; height = b;
	}
	~Shape() {}

	void setWidth(double w) {
		width = w;
	}
	void setHeight(double h) {
		height = h;
	}
	virtual double area() = 0;

protected:
	double width;
	double height;
};

class Rectangle : public Shape {
public:
	Rectangle(double a = 0, double b = 0) :Shape(a, b) { }

	double area() {
		return (width * height);
	}
};
// Circle class definition
class Circle : public Shape {
public:
    Circle(double radius = 0) : Shape(radius, 0) { }

    double area() {
        return PI * width * width; // Use width as radius
    }
};

// Triangle class definition
class Triangle : public Shape {
public:
    Triangle(double base = 0, double height = 0) : Shape(base, height) { }

    double area() {
        return (width * height) / 2; // Base * Height / 2
    }
};

#endif
