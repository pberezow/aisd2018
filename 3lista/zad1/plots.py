import numpy as np
import matplotlib.pyplot as plt
import csv

def genData(n):
    dataRadix = np.genfromtxt('radix.csv', delimiter=',', names=['size', 'comps', 'swaps', 'time'])
    dataMerge = np.genfromtxt('merge.csv', delimiter=',', names=['size', 'comps', 'swaps', 'time'])

    sizeRadix = []
    timeRadix = []
    compsRadix = []
    swapsRadix = []

    sizeMerge = []
    timeMerge = []
    compsMerge = []
    swapsMerge = []

    cnRadix = []
    snRadix = []

    cnMerge = []
    snMerge = []

    for i in range(1, 100):
        sizeRadix.append(dataRadix['size'][i*n-1])
        timeRadix.append(np.mean(dataRadix['time'][(n-1)*i:n*i])/1000000)
        compsRadix.append(np.mean(dataRadix['comps'][n*(i-1):n*i]))
        swapsRadix.append(np.mean(dataRadix['swaps'][(n-1)*i:n*i]))

        sizeMerge.append(dataMerge['size'][i*n-1])
        timeMerge.append(np.mean(dataMerge['time'][(n-1)*i:n*i])/1000000)
        compsMerge.append(np.mean(dataMerge['comps'][n*(i-1):n*i]))
        swapsMerge.append(np.mean(dataMerge['swaps'][(n-1)*i:n*i]))

    for i in range(0, 99):
        cnRadix.append(compsRadix[i] / sizeRadix[i])
        snRadix.append(swapsRadix[i] / sizeRadix[i])

        cnMerge.append(compsMerge[i] / sizeMerge[i])
        snMerge.append(swapsMerge[i] / sizeMerge[i])

    plt.figure(1, figsize=(15, 10))

    plt.subplot(311)
    plt.plot(sizeRadix, timeRadix, label='Radix')
    plt.plot(sizeMerge, timeMerge, label='Merge')
    plt.xlabel('size')
    plt.ylabel('time(ms)')
    plt.title('Zaleznosc czasu od rozmiaru')
    plt.grid(True)
    plt.legend(loc='upper left')

    plt.subplot(312)
    plt.plot(sizeRadix, compsRadix, label='Radix')
    plt.plot(sizeMerge, compsMerge, label='Merge')
    plt.xlabel('size')
    plt.ylabel('comps')
    plt.title('Zaleznosc porownan od rozmiaru')
    plt.grid(True)
    plt.legend(loc='upper left')

    plt.subplot(313)
    plt.plot(sizeRadix, swapsRadix, label='Radix')
    plt.plot(sizeMerge, swapsMerge, label='Merge')
    plt.xlabel('size')
    plt.ylabel('swaps')
    plt.title('Zaleznosc zamian od rozmiaru')
    plt.grid(True)
    plt.legend(loc='upper left')

    plt.figure(2, figsize=(10, 6))

    plt.subplot(211)
    plt.plot(sizeRadix, cnRadix, label='Radix')
    plt.plot(sizeMerge, cnMerge, label='Merge')
    plt.xlabel('size')
    plt.ylabel('comps / size')
    plt.grid(True)
    plt.legend(loc='upper left')

    plt.subplot(212)
    plt.plot(sizeRadix, snRadix, label='Radix')
    plt.plot(sizeMerge, snMerge, label='Merge')
    plt.xlabel('size')
    plt.ylabel('swaps / size')
    plt.grid(True)
    plt.legend(loc='upper left')

    plt.show()

genData(100)
