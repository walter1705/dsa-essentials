#include <iostream>
#include <numeric>
#include <algorithm>
#include <bits/stdc++.h>
using namespace std;

int solution(vector<int> nums, size_t siz) {
  int gcdMin = INT_MAX;
  int range = 10^18;
  for (int i = 2 ; i <= range ; i++) {
    for (size_t j = 0 ; j < siz; j++ ){
      int div = gcd(i, nums[j]);

      if(div == 1) {
        gcdMin = (i < gcdMin) ? i : gcdMin;
      } 
    }
  }
  return (gcdMin != INT_MAX) ? gcdMin : -1;
}

int main() {
  int n;
  cin >> n;
  for (int i = 0 ; i < n ; i++) {
    size_t siz;
    vector<int> nums;
    cin >> siz;

    for (size_t j = 0 ; j < siz ; j++) {
      int a;
      cin >> a;
      nums.push_back(a);
    }

    cout << solution(nums, siz) << "\n";
  }

  return 0;
}
