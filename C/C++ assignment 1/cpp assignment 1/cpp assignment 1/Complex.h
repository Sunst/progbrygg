// Uppsala University
// Department Of IT
// Programming bridging course Autumn 2013
// C assignment 4
// Student: Knut Lorenzen 810326-T296
//
//  Complex.h
//  cpp assignment 1
//
//  Created by Knut Lorenzen on 31/10/2013.
//  Copyright (c) 2013 Knut Lorenzen. All rights reserved.
//

#ifndef __cpp_assignment_1__Complex__
#define __cpp_assignment_1__Complex__

#include <iostream>

class Complex {
    
    double real;
    double im;
    
public:
    Complex();
    Complex( double real, double im );
    void   setReal( double real );
    double getReal();
    void   setIm( double  im);
    double getIm();
    Complex operator+( Complex x );
    Complex operator+( double x );
    Complex operator-( Complex x );
    Complex operator-( double x );
    Complex operator*( Complex x );
    Complex operator*( double x );
    Complex operator/( Complex x );
    Complex operator/( double x );
    double abs();
};

#endif /* defined(__cpp_assignment_1__Complex__) */
