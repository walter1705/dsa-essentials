# B. Pasha and String

## Problem Statement

Pasha received a string `s` for his birthday, consisting of lowercase Latin letters. He decides to modify it over `m` days. Each day, he chooses a position `ai` and reverses the substring from position `ai` to position `|s| - ai + 1`. The value `2·ai` is guaranteed to be less than or equal to the string length.

## Input

- First line: string `s` (2 ≤ |s| ≤ 2·10⁵) consisting of lowercase Latin letters
- Second line: integer `m` (1 ≤ m ≤ 10⁵) representing the number of days
- Third line: `m` space-separated integers `ai` where (1 ≤ ai and 2·ai ≤ |s|)

## Output

Print the final string after all `m` transformations.

## Examples

### Example 1

```
Input:
abcdef
1
2

Output:
aedcbf
```

### Example 2

```
Input:
vwxyz
2
2 2

Output:
vwxyz
```

### Example 3

```
Input:
abcdef
3
1 2 3

Output:
fbdcea
```
