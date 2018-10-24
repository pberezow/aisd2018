import numpy as np
import matplotlib.pyplot as plt
import csv

def genData(n):
    dataInsert = np.genfromtxt('insert.csv', delimiter=',', names=['size', 'comps', 'swaps', 'time'])
    dataQuick = np.genfromtxt('quick.csv', delimiter=',', names=['size', 'comps', 'swaps', 'time'])
    dataMerge = np.genfromtxt('merge.csv', delimiter=',', names=['size', 'comps', 'swaps', 'time'])

    sizeInsert = []
    timeInsert = []
    compsInsert = []
    swapsInsert = []

    sizeQuick = []
    timeQuick = []
    compsQuick = []
    swapsQuick = []

    sizeMerge = []
    timeMerge = []
    compsMerge = []
    swapsMerge = []

    for i in range(1, 100):
        sizeInsert.append(dataInsert['size'][i*n-1])
        timeInsert.append(np.mean(dataInsert['time'][(n-1)*i:n*i])/1000000)
        compsInsert.append(np.mean(dataInsert['comps'][n*(i-1):n*i]))
        swapsInsert.append(np.mean(dataInsert['swaps'][(n-1)*i:n*i]))

        sizeQuick.append(dataQuick['size'][i*n-1])
        timeQuick.append(np.mean(dataQuick['time'][(n-1)*i:n*i])/1000000)
        compsQuick.append(np.mean(dataQuick['comps'][n*(i-1):n*i]))
        swapsQuick.append(np.mean(dataQuick['swaps'][(n-1)*i:n*i]))

        sizeMerge.append(dataMerge['size'][i*n-1])
        timeMerge.append(np.mean(dataMerge['time'][(n-1)*i:n*i])/1000000)
        compsMerge.append(np.mean(dataMerge['comps'][n*(i-1):n*i]))
        swapsMerge.append(np.mean(dataMerge['swaps'][(n-1)*i:n*i]))

    for i in range(0, 99):
        cnInsert.append(compsInsert[i] / sizeInsert[i])
        snInsert.append(swapsInsert[i] / sizeInsert[i])

        cnQuick.append(compsQuick[i] / sizeQuick[i])
        snQuick.append(swapsQuick[i] / sizeQuick[i])

        cnMerge.append(compsMerge[i] / sizeMerge[i])
        snMerge.append(swapsMerge[i] / sizeMerge[i])

    plt.figure(1, figsize=(15, 10))

    plt.subplot(311)
    plt.plot(sizeInsert, timeInsert, label='Insert')
    plt.plot(sizeQuick, timeQuick, label='Quick')
    plt.plot(sizeMerge, timeMerge, label='Merge')
    plt.xlabel('size')
    plt.ylabel('time(ms)')
    plt.title('Zaleznosc czasu od rozmiaru')
    plt.grid(True)
    plt.legend(loc='upper left')

    plt.subplot(312)
    plt.plot(sizeInsert, compsInsert, label='Insert')
    plt.plot(sizeQuick, compsQuick, label='Quick')
    plt.plot(sizeMerge, compsMerge, label='Merge')
    plt.xlabel('size')
    plt.ylabel('comps')
    plt.title('Zaleznosc porownan od rozmiaru')
    plt.grid(True)
    plt.legend(loc='upper left')

    plt.subplot(313)
    plt.plot(sizeInsert, swapsInsert, label='Insert') 
    plt.plot(sizeQuick, swapsQuick, label='Quick')
    plt.plot(sizeMerge, swapsMerge, label='Merge')
    plt.xlabel('size')
    plt.ylabel('swaps')
    plt.title('Zaleznosc zamian od rozmiaru')
    plt.grid(True)
    plt.legend(loc='upper left')

    plt.figure(2, figsize=(10, 6))

    plt.subplot(211)
    plt.plot(sizeInsert, cnInsert, label='Insert')
    plt.plot(sizeQuick, cnQuick, label='Quick')
    plt.plot(sizeMerge, cnMerge, label='Merge')
    plt.xlabel('size')
    plt.ylabel('comps / size')
    plt.grid(True)
    plt.legend(loc='upper left')

    plt.subplot(212)
    plt.plot(sizeInsert, snInsert, label='Insert')
    plt.plot(sizeQuick, snQuick, label='Quick')
    plt.plot(sizeMerge, snMerge, label='Merge')
    plt.xlabel('size')
    plt.ylabel('swaps / size')
    plt.grid(True)
    plt.legend(loc='upper left')

    plt.show()

genData(1000)