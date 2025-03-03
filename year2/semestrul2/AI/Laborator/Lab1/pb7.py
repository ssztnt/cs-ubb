import heapq

def find_kth_largest(nums, k):
    # Folosim un min-heap pentru a menține cele mai mari k elemente
    min_heap = []
    for num in nums:
        heapq.heappush(min_heap, num)
        if len(min_heap) > k:
            heapq.heappop(min_heap)  # Eliminăm cel mai mic element din heap
    return min_heap[0]  # Rădăcina heap-ului este al k-lea cel mai mare element

def find_kth_biggest(nums , k):
    return sorted(nums)[-k] # Sortăm șirul și returnăm al k-lea cel mai mare element

def meniu():
    print("Meniu:")
    print("1. Folosește funcția find_kth_largest")
    print("2. Folosește funcția find_kth_biggest")

    optiune = input("Alege o opțiune (1/2): ")

    if optiune == "1":
        nums = input("Introdu elementele șirului separate prin spațiu: ").split()
        nums = [int(x) for x in nums]
        k = int(input("Introdu valoarea lui k: "))
        rezultat = find_kth_largest(nums, k)
        print(f"Al {k}-lea cel mai mare element este: {rezultat}")

    elif optiune == "2":
        nums = input("Introdu elementele șirului separate prin spațiu: ").split()
        nums = [int(x) for x in nums]
        k = int(input("Introdu valoarea lui k: "))
        rezultat = find_kth_biggest(nums, k)
        print(f"Al {k}-lea cel mai mare element este: {rezultat}")

    else:
        print("Opțiune invalidă! Te rog să alegi 1 sau 2.")

meniu()