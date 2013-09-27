from numpy import *
from numpy import matlib
from Tkinter import *
from tkMessageBox import *
import time
from random import gauss

############################################################################################
## builtin types computation section

# startComputation
# computes the eigenvalue and eigenvector using lists
# N (integer)    : size of the matrix
# e_limit (float): smallest difference between two vectors at which to stop the computation 
# maxIt (integer): Maximum number of iterations
#
def startComputation(N, e_limit, maxIt):
    m = generateRandMatrix(N)
    #initialise x
    x = []
    for i in range(N):
        x.append(1)
    itCnt = 0
    success = False
    start = time.clock()         
    while itCnt < maxIt and not success:
        itCnt += 1
        y = multiply(N, m, x)         
        y_max = max(y)
        new_x = []
        for i in range(N):
            new_x.append( y[i]/y_max )
        if vectorChange(N, x, new_x) <= e_limit:
            success = True
        x = new_x            
    showResult(success, N, y_max, x, time.clock()-start, itCnt)
   
   
# multiply
# multiplies a square matrix represented by nested lists with a vector
# N (integer)             : Size of the matrix and the vector
# m (list(list(integer))) : A matrix with each row represented by a list of integers
# v (list(integer))       : A column vector represented as a list of integers
def multiply(N, m, v):    
    res = []
    for i in range(N):
        res.append(0)    
    for i in range(N) :        
        for j in range(N):
            res[i] += m[i][j] * v[j]        
    return res
   
# generateRandMatrix
# generates a square matrix represented as nested lists, with a gaussian distribution of 
# mean 0 and sigma 1
# N (integer): size of the matrix
#
def generateRandMatrix(N):
    m = []
    for j in range(N):
        # r is a row of the matrix
        r = []
        for i in range(N):
            r.append(gauss(0, 1))
        m.append(r)
    return m


# vectorChange
# computes the the difference e_k between two vectors 
# N (integer) : Length of the vectors (must be identical)
# a (list(integer)) : A vector 
# b (list(integer)) : A vector
#
def vectorChange(N, a, b):
    i = 0
    s = 0.
    while i < N:
        diff = a[i] - b[i]
        s += diff * diff
        i+=1
    s = s/N
    return sqrt(s) 


########################################################################################
## numpy computation section


# startComputation_np
# computes the eigenvalue and eigenvector using numpy matrix objects
# N (integer)    : size of the matrix
# e_limit (float): smallest difference between two vectors at which to stop the computation 
# maxIt (integer): Maximum number of iterations
#
def startComputation_np(N, e_limit, maxIt):    
    m = matlib.randn(N, N)
    x = matlib.ones((N,1))  
    start = time.clock()     
    itCnt = 0
    success = False
    while itCnt < maxIt and not success:
        itCnt += 1
        y = m * x
        y_max = y.max()
        x_new = y / y_max
        e_k = vectorChange_np(N, x, x_new)
        x = x_new
        if e_k <= e_limit :
            success = True
    showResult(success, N, y_max, x.tolist(), time.clock()-start, itCnt)  
        

# vectorChange_np
# computes the the difference e_k between two vectors 
# N (integer) : Length of the vectors (must be identical)
# a (np.matrix) : A vector 
# b (np.matrix) : A vector
#                    
def vectorChange_np(N, a, b):
    i = 0
    s = 0.
    while i < N:
        diff = a[i,0] - b[i,0]
        s += diff * diff
        i+=1        
    s = s/N
    return sqrt(s)



########################################################################################
## Graphical UI section


# showResult
# displays a message box with computation results
# success (Bool) : True if an eigenvector could be found. The eigenvalue and the first 10 
#                  elements of the eigenvector will be displayed. If False, a message will
#                  be shown instead
# (optional) N (integer)            : Length of the eigenvector
# (optional) eigenvalue (float)     : The eigenvalue
# (optional) eigenvector (list(integer)) : The eigenvecor in form of a list of integers
# (optional) timeLapse (float) : The time needed to compute in seconds
   
def showResult(success, N=None, eigenvalue=None, eigenvector=None, timeLapse=None, itCount=None):
    if success:
        msg = "eigenvalue:\n" + str(eigenvalue) + "\n\neigenvector:\n"        
        i = 0
        while i < N and i < 10:
            msg += str(eigenvector[i]) + "\n"
            i+=1
        if i >=10:
            msg += "..."              
    else:
        msg = "did not converge"        
    if timeLapse != None:
        msg += "\n\n elapsed time: " + str(timeLapse) + " seconds"          
    if success and itCount != None:
        msg += "\n\niteration count: " + str(itCount)
    showinfo("Result", msg)     

# onComputeClick
# Callback for the 'Compute' button. Evaluates the user input  and starts the computation
def onComputeClick():
    N = int(matrixSizeInput.get())    
    epsilon = float(epsilonInput.get())
    if numPySelectionInput.get() == "no":
        startComputation(N, epsilon, 1000)
    elif numPySelectionInput.get() == "yes":
        startComputation_np(N, epsilon, 1000)
    else:
        raise
    
if __name__ == "__main__":   
    # initialise and show the user interface 
    tkRoot = Tk()
    numPySelectionInput = StringVar()
    numPySelectionInput.set("yes")
    matrixSizeInput     = StringVar()
    matrixSizeInput.set(3)
    epsilonInput        = StringVar()
    epsilonInput.set(1e-6)    
    f1 = LabelFrame(tkRoot, text="Method")
    f1.pack()
    Radiobutton(f1,command = None,
                 text = "Not NumPy",
                 value = "no",
                 variable = numPySelectionInput).pack(anchor=W)
    Radiobutton(f1,command = None,
                 text = "NumPy",
                 value = "yes",
                 variable = numPySelectionInput).pack(anchor=W)
    f2 = LabelFrame(tkRoot, text="Parameters")
    f2.pack()
    Label(f2, text="Enter the size of the matrix").pack()
    Entry(f2, textvariable=matrixSizeInput).pack()
    Label(f2, text="epsilon").pack()
    Entry(f2, textvariable=epsilonInput).pack()    
    Button(tkRoot, text="Compute", command=onComputeClick).pack(side=LEFT, fill=X)
    Button(tkRoot, text="Exit", command=sys.exit).pack(side=RIGHT, fill=Y)
    mainloop()