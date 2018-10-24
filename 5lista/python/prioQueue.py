import fileinput

class Element:
    def __init__(self, val, prio):
        self.val = val
        self.prio = prio

class PrioQueue:
    def __init__(self):
        self.size = 0
        self.heap = []

    def empty(self):
        if(len(self.heap) == 0):
            return True
        else:
            return False
    
    def insert(self, x, p):
        i = self.size
        j = (i-1)//2
        newElement = Element(x, p)
        self.heap.append(newElement)
        self.size += 1
        self.fixElements(self.size-1)
        # while i > 0 and self.heap[j].prio > p:
        #     self.heap[i] = self.heap[j]
        #     i = j
        #     j = (i-1)//2
        # self.heap[i] = newElement
    
    def pop(self):
        # print("size: ", self.size)
        if self.size == 0:
            return None
            
        val = self.heap[0].val
        self.size -= 1

        lastElement = self.heap[self.size]
        del self.heap[self.size]
        if self.size == 0:
            return val
        i = 0
        j = 1
        while j < self.size:
            if j+1 < self.size and self.heap[j+1].prio < self.heap[j].prio:
                j += 1
            if lastElement.prio <= self.heap[j].prio:
                break
            self.heap[i] = self.heap[j]
            i = j
            j = 2*j+1
        self.heap[i] = lastElement
        return val

    def top(self):
        if self.size > 0:
            return self.heap[0].val
        else:
            return None
    
    def priority(self, x, p):
        self.findElements(x, p, 0)

    def findElements(self, x, p, idx):
        if self.size > idx:
            if self.heap[idx].val == x and self.heap[idx].prio > p:
                self.heap[idx].prio = p
                self.fixElements(idx)
            left = 2 * idx + 1
            self.findElements(x, p, left)
            right = 2 * idx + 2
            self.findElements(x, p, right)
    
    def fixElements(self, idx):
        toFix = self.heap[idx]
        i = idx
        j = (idx-1)//2

        while j >= 0 and self.heap[j].prio > toFix.prio:
            self.heap[i] = self.heap[j]
            i = j
            j = (i-1)//2
            if j == 0:
                break
        self.heap[i] = toFix

    def print(self):
        str = ""
        arr = list(self.heap)
        size = self.size-1

        while size >= 0:
            # val = str(arr[0].val)
            # prio = str(arr[0].prio)
            str += "(" + '{}'.format(arr[0].val) + ", " + '{}'.format(arr[0].prio) + ") "
            lastElement = arr[size]
            arr[size] = None

            i = 0
            j = 1

            while j < size:
                if j+1 < size and arr[j+1].prio < arr[j].prio:
                    j += 1
                if lastElement.prio <= arr[j].prio:
                    break
                arr[i] = arr[j]
                i = j
                j = 2*j+1
            arr[i] = lastElement
            size -= 1
        print(str)
        return str

    def make(self, lines):
        if lines[0] == "insert":
            self.insert(int(lines[1]), float(lines[2]))
        elif lines[0] == "empty":
            if self.empty():
                print(0)
            else:
                print(1)
        elif lines[0] == "top":
            if self.empty():
                print()
            else:
                print(self.top())
        elif lines[0] == "pop":
            if self.empty():
                print()
            else:
                print(self.pop())
        elif lines[0] == "priority":
            self.priority(int(lines[1]), float(lines[2]))
        elif lines[0] == "print":
            self.print()
        else:
            print("Wrong arg!")


pq = PrioQueue()
for line in fileinput.input():
    lines = line.split()
    pq.make(lines)
    
# pq = PrioQueue()
# pq.insert(10, 0)
# pq.insert(6, 3.12)
# pq.insert(13, 8.5)
# pq.insert(24, 2.7)
# pq.insert(100, 0)
# pq.insert(13, 16)
# pq.insert(10, 6)
# pq.insert(10, 5)
# pq.print()

# print(pq.pop())
# print(pq.pop())
# pq.print()

# pq.priority(10, 0)
# pq.print()

# print(pq.pop())
# print(pq.pop())
# print(pq.pop())
# print(pq.pop())
# print(pq.pop())
# print(pq.pop())
# print(pq.pop())
# print(pq.pop())