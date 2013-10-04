// Uppsala University
// Department Of IT
// Programming bridging course Autumn 2013
// C assignment 2
// Student: Knut Lorenzen 810326-T296


#include <stdio.h>
#include <math.h>


double newton(double x0, double epsilon, double (*f_ptr)(double), double (*fprim_ptr)(double))
{
    double  x_i,
            x_i1  = x0,
            delta = 2*epsilon;
    
    while ( delta > epsilon )
    {
        x_i = x_i1;
        x_i1 = x_i - f_ptr(x_i)/fprim_ptr(x_i);
        delta = fabs( x_i1 - x_i );
    }
    
    return x_i1;
}



double f(double v)
{
    double v_2 = v*v;
    double v_3 = v_2*v;
    return 70*v_3 - 3*v_2 + 4*v-16;
}



double fprim(double v)
{
    return 210*v*v - 6*v + 4;
}


int main()
{
    
    double x0=1.0, eps=1e-06;
    
    printf( "x0:" );
    scanf( "%lf", &x0);
    printf( "epsilon:" );
    scanf( "%lf", &eps);
    
    double r = newton(x0, eps, &f, &fprim);
    printf("root: %f", r );
    
    return 0;
}
