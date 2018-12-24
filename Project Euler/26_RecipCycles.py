#SY 2018
#github.com/apple-polestar 
#Finds number with longest cycle of repeating decimals (regarding its reciprocal)

import math
from decimal import *

def main():
    grt = int(input("Upper limit: "))
    longestCycle(grt)
def longestCycle(greatest):

    length = 0
    num = 0
    for i in range(greatest, 1, -1):
        if length >= i:
            break
        remainders = [0] * i
        val = 1
        pos = 0
        while remainders[val] == 0 and val != 0:
            remainders[val] = pos
            val *= 10
            val %= i
            pos += 1
        if pos - remainders[val] > length:
            num = i
            length = pos - remainders[val]
    print("Num w/ longest cycle: {0} // cycle length: {1}".format(num, length))
if __name__ == "__main__":
    main()
