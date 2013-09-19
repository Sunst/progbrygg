from numpy import *
from numpy import matlib
from Tkinter import *
from tkMessageBox import *
import time
from random import gauss


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
    showResult(success, N, y_max, x, time.clock()-start)
   
   
def multiply(N, m, v):    
    res = []
    for i in range(N):
        res.append(0)    
    for i in range(N) :        
        for j in range(N):
            res[i] += m[i][j] * v[j]        
    return res
   
def generateRandMatrix(N):
    m = []
    for j in range(N):
        r = []
        for i in range(N):
            r.append(gauss(0, 1))
        m.append(r)
    return m


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
    showResult(success, N, y_max, x.tolist(), time.clock()-start)  
        
        
def showResult(success, N, eigenvalue=None, eigenvector=None, timeLapse=None):
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
        
    showinfo("Result", msg)        
                    
                    
                    
def vectorChange_np(N, a, b):
    i = 0
    s = 0.
    while i < N:
        diff = a[i,0] - b[i,0]
        s += diff * diff
        i+=1        
    s = s/N
    return sqrt(s)

def vectorChange(N, a, b):
    i = 0
    s = 0.
    while i < N:
        diff = a[i] - b[i]
        s += diff * diff
        i+=1        
    s = s/N
    return sqrt(s)    

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