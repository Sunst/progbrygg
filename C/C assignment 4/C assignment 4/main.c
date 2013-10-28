// Uppsala University
// Department Of IT
// Programming bridging course Autumn 2013
// C assignment 4
// Student: Knut Lorenzen 810326-T296

#include <stdio.h>
#include <math.h>
#include <stdlib.h>

static double k=300;
static double m=5;
static double g=9.81;
static double my=0.1;

typedef struct pair {
    double y,z;
} pair;


int sign(double x)
{
    if (x < 0)      return -1;
    else if (x > 0) return 1;
    else            return 0;
}


/* the p function 
 dx over dt, or 
 dy over dx aka speed */
double p(double x, // time
         double y, // position
         double z) // speed
{
    return z;
}


/* the q function 
 dy over dt, or 
 dz over dx aka acceleration
 */
double q(double x, // time
         double y, // position
         double z) // speed
{
    return -y*(k/m)-my*g*sign(z);
}


/*
 the predictor/corrector function, returns one pair of y and z for each call
 */
pair predcorr(double x, double y, double z, double h)
{
    pair res;
    
    double yp = y + h * p(x, y, z);
    double zp = z + h * q(x, y, z);
    
    res.y = y + 0.5*h*(p(x, y, z) + p(x+h, yp, zp));
    res.z = z + 0.5*h*(q(x, y, z) + q(x+h, yp, zp));
    return res;
}


int main() {
    /*
     define h and the limit epsilon iterate over friction values
     create two arrays x and y */
    
    double const h       = 0.005,
                t0       = 0.0,
                epsilon  = 0.1;
    
    const int steps = 601;
    double x[steps],
           y[steps];
    
    x[0] = 0.2; // postion
    y[0] = 0.0; // speed
    
    printf( "Start values:\nt = %lf\nx = %lf\ny = %lf\nh = %lf\nmy = %lf\nepsilon = %lf\n\n",
           t0, x[0], y[0], h, my, epsilon );
    
    /*
     iterate over the pred-corr function to fill
     x and y with values */
    
    for ( int i=1; i<steps; i++ )
    {
        pair p = predcorr(i*h,    // x aka t aka time
                          x[i-1], // y aka x aka location
                          y[i-1], // z aka y aka speed
                          h);
        x[i] = p.y;
        y[i] = p.z;
    }
    
    printf( "t, x, and y for my = %lf\n\n", my );
    for ( int i=0; i<steps; i++ )
    {
        printf( "%lf %lf %lf\n", i*h, x[i], y[i] );
    }
    
    /*
     analyze the result according to the proplem
     */
    double limit = 0.1;
    printf( "limit:" );
    scanf( "%lf", &limit );

    for (; my < 0.9; my += 0.1 )
    {
        for ( int i=1; i<steps; i++ )
        {
            pair p = predcorr( i*h,    // x aka t aka time
                               x[i-1], // y aka x aka location
                               y[i-1], // z aka y aka speed
                               h );
            x[i] = p.y;
            y[i] = p.z;
        }
        int t_damp = 0;
        // find abs(x[i]) < 0.1
        for ( int i=1; i<steps; i++ )
            if ( fabs(x[i]) >= limit )
                t_damp = i;
        
        printf( "The time when abs(x) permanently drops below  %lf for  my =  %lf is  %lf\n",
                 limit, my, t_damp*h);
    }
}

