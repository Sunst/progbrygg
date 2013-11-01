//
//  NewtonRaphsonSub.cpp
//  cpp assignment 1
//
//  Created by Knut Lorenzen on 31/10/2013.
//  Copyright (c) 2013 Knut Lorenzen. All rights reserved.
//

#include "NewtonRaphsonSub.h"
#include "Complex.h"

Complex NewtonRaphsonSub::f( Complex x ){
   
    return x*x + x * 2 + 2;
}


Complex NewtonRaphsonSub::f_prim( Complex x ){
    
    return x*2 + 2;
}