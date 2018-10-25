import math

def find_divisors(num):
    divisors = []
    for i in range (1, num-1):
        if num % i == 0:
            divisors.append(i)
    return divisors

def s(lis_t):
    summ = 0
    for i in range (0, len(lis_t)):
        summ += lis_t[i]
    return summ

def has_amicable(n1):
    n2 = s(find_divisors(n1))
    if s(find_divisors(n2)) == n1 and n1 != n2:
        return True
    return False

def find_amicables(top):
    amicables = []
    for i in range(1, top-1):
        if has_amicable(i):
            if i not in amicables:
                amicables.append(i)
            if s(find_divisors(i)) not in amicables:
                amicables.append(s(find_divisors(i)))
    print(amicables)
    return amicables

def main():
    maximum = input("Max: ")
    print(s(find_amicables(maximum)))

if __name__ == "__main__":
    main()
    
            
