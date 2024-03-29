// Uppsala University
// Department Of IT
// Programming bridging course Autumn 2013
// C++ assignment 1
// Student: Knut Lorenzen 810326-T296
//
//  NewtonRaphson.h
//
//  Created by Knut Lorenzen on 31/10/2013.
//  Copyright (c) 2013 Knut Lorenzen. All rights reserved.
//

#ifndef __cpp_assignment_1__NewtonRaphson__
#define __cpp_assignment_1__NewtonRaphson__

#include <iostream>

#endif /* defined(__cpp_assignment_1__NewtonRaphson__) */

class Complex;

class NewtonRaphson {
    
  public:
    Complex run( double epsilon, Complex x0 );
    
    virtual Complex f( Complex x )=0;
    virtual Complex f_prim( Complex x )=0;
};