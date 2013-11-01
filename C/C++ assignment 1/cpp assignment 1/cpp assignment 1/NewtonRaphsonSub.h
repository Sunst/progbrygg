//
//  NewtonRaphsonSub.h
//  cpp assignment 1
//
//  Created by Knut Lorenzen on 31/10/2013.
//  Copyright (c) 2013 Knut Lorenzen. All rights reserved.
//

#ifndef __cpp_assignment_1__NewtonRaphsonSub__
#define __cpp_assignment_1__NewtonRaphsonSub__

#include <iostream>

#endif /* defined(__cpp_assignment_1__NewtonRaphsonSub__) */

#include "NewtonRaphson.h"

class NewtonRaphsonSub : public NewtonRaphson {

  public:
    virtual Complex f( Complex x );
    virtual Complex f_prim( Complex x );
};