// Uppsala University
// Department Of IT
// Programming bridging course Autumn 2013
// C++ assignment 1
// Student: Knut Lorenzen 810326-T296
//
//  Complex.cpp
//
//  Created by Knut Lorenzen on 31/10/2013.
//  Copyright (c) 2013 Knut Lorenzen. All rights reserved.
//

#include "Complex.h"
#include <math.h>

Complex::Complex(){
    
    real = 0.0;
    im = 0.0;
}


Complex::Complex( double real, double im ){
    
    this->real = real;
    this->im = im;
}


double Complex::getReal(){
    
    return real;
}



double Complex::getIm(){
    
    return im;
}


Complex Complex::operator+( Complex x ){
    
    double  a = real,
            b = im,
            c = x.real,
            d = x.im;
    
    return Complex( a + c,
                    b + d );
}



Complex Complex::operator+( double x ){
    
    return Complex( real + x, im );
}


Complex Complex::operator-( Complex x ){
    
    double  a = real,
            b = im,
            c = x.real,
            d = x.im;

    return Complex( a - c,
                    b - d );
}



Complex Complex::operator*( Complex x ){
    
    double  a = real,
            b = im,
            c = x.real,
            d = x.im;

    return Complex( a*c - b*d,
                    b*c + a*d );
}


Complex Complex::operator*( double x ){
    
    return Complex( real*x, im*x );
}


Complex Complex::operator/(Complex x){
    
    double a = real,
           b = im,
           c = x.real,
           d = x.im;
    
    return Complex( (a*c + b*d)/(c*c + d*d),
                    (b*c - a*d)/(c*c + d*d) );
}




double Complex::abs(){
    
    return sqrt( real*real + im*im );
}

