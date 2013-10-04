// Uppsala University
// Department Of IT
// Programming bridging course Autumn 2013
// C assignment 1
// Student: Knut Lorenzen 810326-T296


#include <stdio.h>
#include <math.h>

static const double E = 20;
static const double L = 5;
static const double R = 10;
static const double h = 0.01;


double dydx(double x, double y)
{
    return E/L - R/L*y;
}



double predcorr(double t, double I)
{
    // y_p = y_i + h * f(x_i, y_i)    
    double I_p = I + h* dydx(t, I);
    
    // y_i + 1/2 * h * (f(x_i,y_i) + f(x_i + h, y_p))
    return I + 0.5 * h * ( dydx(t, I ) + dydx(t + h, I_p) ) ;
}



int main()
{
    /* an iteration over time here, call predcorr for each step
     */
    double I = 0.0;
    for ( double t = 0.0; t < 1.0 ; t += h )
    {
        //printf( "t = %f\n", t );
        I = predcorr( t, I );
    }
    printf("I(t=1) = %f\n", I );
    return 0;
}