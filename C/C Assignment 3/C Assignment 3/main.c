// Uppsala University
// Department Of IT
// Programming bridging course Autumn 2013
// C assignment 3
// Student: Knut Lorenzen 810326-T296


#include <stdio.h>
#include <math.h>
#include <assert.h>
#include <stdlib.h>


/* calculate the sum of all numbers in an array */
double sumone(double *v, int n)
{
    double s = 0.0;
    for ( int i = 0; i < n; i++ )
        s += v[i];
    return s;
}



/* calculate the dot product of two arrays */
double sumtwo(double *v, double *w, int n)
{    
    double s = 0.0;
    for ( int i = 0; i < n; i++ )
        s += v[i] * w[i];
    return s;
}


/*
 do the calculations, parameters are the arrays { x},{ y} and { n} (the size of the array)
 results are { a} and { b} */
double *solve(double *x, double *y, int n)
{
    // calc sumx, sumy, sumxy and sumx2
    double sumx = sumone(x, n);
    double sumy = sumone(y, n);
    double sumxy = sumtwo(x, y, n);
    double* x2 = (double*)malloc(n*sizeof(double));
    for ( int i = 0; i < n; i++ )
        x2[i] = x[i]*x[i];
    double sumx2 = sumone(x2, n);
    free(x2);
    
    // calculate D
    double D = sumx2 * n - sumx * sumx;
    
    //calculate a
    double a = (n*sumxy - sumx * sumy)/D;
    
    //calculate b
    double b = (sumx2 * sumy - sumxy * sumx )/D;
    
    double* ab = (double*)malloc(2*sizeof(double));
    ab[0] = a;
    ab[1] = b;
    return ab;
}



int main()
{
    /*
     create two arrays, fill them with numbers call solve to get a and b. Then calculate k and L
     */
    int n = 0;
    printf( "how many pairs?" );
    scanf( "%i", &n);

    double* x = (double*)malloc(n*sizeof(double));
    double* F = (double*)malloc(n*sizeof(double));
        
    for ( int i=0; i<n; i++ )
    {
        printf( "give a pair:" );
        scanf( "%lf %lf", &x[i], &F[i] );
    }
    
    double* ab = solve(x, F, n);
    
    printf( "The corresponding line is: %lfx + %lf\n", ab[0], ab[1] );
    
    double L = -ab[1]/ab[0];
    printf( "This means that k = %lf and L = %lf\n", ab[0], L );
    
    free( ab );
    free( x );
    free( F );
    return 0;
}