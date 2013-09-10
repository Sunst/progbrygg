#from numpy  import *
from Tkinter import *

# my_widget = Label(tkRoot, text="Hello world")
# my_widget.pack()
# tkRoot.mainloop()

tkRoot = Tk()
numPySelectionInput = StringVar()
numPySelectionInput.set("yes")
matrixSizeInput     = StringVar()
matrixSizeInput.set(3)
epsilonInput        = StringVar()
epsilonInput.set(42)

def startComputation(matrixSize, epsilon):
    print "starting computation using builtin types, N =" + matrixSize + " epsilon = " + epsilon

def startComputationNumPy(matrixSize, epsilon):
    print "starting computation using numPy, N =" + matrixSize + " epsilon = " + epsilon

def onComputeClick():
    N = matrixSizeInput.get()    
    epsilon = epsilonInput.get()
    #validate N and epsilon!    
    if numPySelectionInput.get() == "no":
        startComputation(N, epsilon)
    elif numPySelectionInput.get() == "yes":
        startComputationNumPy(N, epsilon)
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