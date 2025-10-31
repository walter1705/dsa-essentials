#include <iostream>
#include <numeric>
#include <algorithm>
#include <bits/stdc++.h>
using namespace std;


int main() {
  size_t n;

  cin >> n;
  vector<int> nums; 

  for(size_t i = 0 ; i < n ; i++) {
    int a;
    cin >> a;
    nums.push_back(a);
  }

  for(size_t i = 0 ; i < n ; i++) {
    int k = nums[i];
    int modK = k % 2;
    for (size_t j = 0 ; j < n ; j++)  {
      int h = nums[j];
      int modH = h % 2;

      if (modK != modH) {
        swap(nums[i], nums[j]);
      }
    }
  }
  
  for (size_t i = 0 ; i < n ; i++) {
    cout << nums[i] << " ";
  }

  return 0;
}
