# Complexitate timp: O(n)
# Complexitate memorie: O(n)

# parametri intrare : n - numar natural
# return : lista de numere binare de la 1 la n
def generate_binary_numbers(n):
    binary_numbers = []
    for i in range(1, n + 1):
        binary_numbers.append(bin(i)[2:])  
    return binary_numbers

# Complexitate timp: O(n)
# Complexitate memorie: O(n)
# parametri intrare : n - numar natural
# return : lista de numere binare de la 1 la n
def generate_binary_numbers_optimized(n):
    binary_numbers = []
    for i in range(1, n + 1):
        binary_numbers.append(format(i, 'b'))
    return binary_numbers

def menu():
    print("1. Generate binary numbers")
    print("2. Generate binary numbers optimized")
    print("3. Exit")
    option = int(input("Option: "))
    if option == 1:
        n = int(input("n = "))
        print(generate_binary_numbers(n))
    elif option == 2:
        n = int(input("n = "))
        print(generate_binary_numbers_optimized(n))
    elif option == 3:
        return
    else:
        print("Invalid option")

    assert generate_binary_numbers(5) == ['1', '10', '11', '100', '101']
    assert generate_binary_numbers(10) == ['1', '10', '11', '100', '101', '110', '111', '1000', '1001', '1010']
    assert generate_binary_numbers_optimized(5) == ['1', '10', '11', '100', '101']
    assert generate_binary_numbers_optimized(10) == ['1', '10', '11', '100', '101', '110', '111', '1000', '1001', '1010']
menu()