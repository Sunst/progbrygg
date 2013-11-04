// Uppsala University
// Department Of IT
// Programming bridging course Autumn 2013
// C++ assignment 1
// Student: Knut Lorenzen 810326-T296
//
//  NewtonRaphson.cpp
//
//  Created by Knut Lorenzen on 31/10/2013.
//  Copyright (c) 2013 Knut Lorenzen. All rights reserved.
//

#include "NewtonRaphson.h"
#include "Complex.h"
#include <math.h>

Complex NewtonRaphson::run( double epsilon, Complex x0 ){
    
    Complex xi1( x0 ),
            xi;
    
    do {
        xi  = xi1;
        xi1 = xi -  f( xi )/f_prim( xi ) ;
        
    } while ( fabs( xi1.abs() - xi.abs() ) > epsilon );
    
    return xi1;
}