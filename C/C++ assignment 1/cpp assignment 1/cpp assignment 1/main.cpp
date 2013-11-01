// Uppsala University
// Department Of IT
// Programming bridging course Autumn 2013
// C assignment 4
// Student: Knut Lorenzen 810326-T296
//
//  main.cpp
//  cpp assignment 1
//
//  Created by Knut Lorenzen on 31/10/2013.
//  Copyright (c) 2013 Knut Lorenzen. All rights reserved.
//

#include <iostream>
#include "NewtonRaphsonSub.h"
#include "Complex.h"

int main(int argc, const char * argv[]){

    NewtonRaphsonSub nr;
    Complex res;
    
    printf( "initial state:");
    double r, i, eps;
    scanf( "%lf %lf", &r, &i );
    printf( "\nepsilon:" );
    scanf( "%lf", &eps );
    Complex x0( r, i );
    
    res = nr.run( eps, x0 );
    
    printf( "result: %lf + %lf i\n", res.getReal(), res.getIm() );
    
    return 0;
}

