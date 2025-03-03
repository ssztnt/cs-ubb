def find_majority_elemenet(nums):
    count = 0
    candidate = None


    for num in nums:
        if count == 0:
            candidate = num
        count += (1 if num == candidate else -1)

    count = 0
    for num in nums:
        if num == candidate:
            count += 1

    return candidate if count > len(nums) // 2 else None

def find_majority_element(nums):
    nums.sort()  # Sortăm șirul
    n = len(nums)
    candidate = nums[n // 2]  # Elementul din mijloc este candidatul

    # Verificăm dacă candidatul este majoritar
    count = 0
    for num in nums:
        if num == candidate:
            count += 1

    if count > n // 2:
        return candidate
    else:
        return None  # Nu există element majoritar

def main():
    while True:
        print("Meniu:")
        print("1. Folosește funcția find_majority_element")
        print("2. Folosește funcția find_majority_elemenet")
        print("3. Ieșire")

        optiune = input("Alege o opțiune (1/2/3): ")

        if optiune == "1":
            nums = input("Introdu elementele șirului separate prin spațiu: ").split()
            nums = [int(x) for x in nums]
            rezultat = find_majority_element(nums)
            if rezultat is not None:
                print(f"Elementul majoritar este: {rezultat}")
            else:
                print("Nu există element majoritar")

        elif optiune == "2":
            nums = input("Introdu elementele șirului separate prin spațiu: ").split()
            nums = [int(x) for x in nums]
            rezultat = find_majority_elemenet(nums)
            if rezultat is not None:
                print(f"Elementul majoritar este: {rezultat}")
            else:
                print("Nu există element majoritar")

        elif optiune == "3":
            print("Ieșire din program. La revedere!")
            break

main()