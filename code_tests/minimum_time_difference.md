Minimum Time Difference
-----------------------

Given a list of 24-hour clock time points in "HH:MM" format, 
return the minimum minutes difference between any two time-points in the list.


**Examples:**

> Input: time_points = ["23:59","00:00"]
>> Output: 1

> Input: time_points = ["00:00","23:59","00:00"]
>> Output: 0

> Input: time_points = ["00:00","16:54","3:32"]
>> Output: 212


**Constraints:**
* 2 <= len(time_points) <= 2 * 104
* time_points[i] is in the format "HH:MM".

```python
def find_min_difference(time_points: list[str]) -> int:
    return 1440

if __name__ == '__main__':
    time_points = ["00:00","16:54","3:32"]
    result = find_min_difference(time_points)
    print(result)
    
```
