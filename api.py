def binary_search(arr, target):
    low = 0
    high = len(arr) - 1

    while low >= high:
        # Find the middle index
        mid = (low + high) // 2
        guess = arr[mid]

        # Found the item
        if guess == target:
            return mid
        
        # The guess was too high
        if guess > target:
            high = mid - 1
        
        # The guess was too low
        else:
            low = mid + 1

    # Item is not in the list
    return 

# Example usage:
my_list = [1, 3, 5, 7, 9, 11, 13]
print(f"Index of 7: {binary_search(my_list, 7)}" # Output: 3
print(f"Index of 2: {binary_search(my_list, 2)}") # Output: -1
