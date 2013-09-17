from numpy import *
from numpy import matlib
from Tkinter import *
from tkMessageBox import *

tkRoot = Tk()
numPySelectionInput = StringVar()
numPySelectionInput.set("yes")
matrixSizeInput     = StringVar()
matrixSizeInput.set(3)
epsilonInput        = StringVar()
epsilonInput.set(1e-6)

def startComputation(N, epsilon ):
    print "starting computation using builtin types, N =" + str(N) + " epsilon = " + str(epsilon)

def startComputation_np(N, e_limit, maxIt):    
    m = matlib.randn(N, N)
    x = matlib.ones((N,1))    
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
    if success:
        msg = "eigenvalue:\n" + str(y_max) + "\n\neigenvector:\n"        
        i = 0
        while i < N and i < 10:
            msg += str(x_new[i,0]) + "\n"
            i+=1
        if i >=10:
            print "..."
    else:
        msg = "did not converge"
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
    

def onComputeClick():
    N = int(matrixSizeInput.get())    
    epsilon = float(epsilonInput.get())
    if numPySelectionInput.get() == "no":
        startComputation(N, epsilon)
    elif numPySelectionInput.get() == "yes":
        startComputation_np(N, epsilon, 1000)
    else:
        raise

def initUi():        
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

    
if __name__ == "__main__":    
    initUi()