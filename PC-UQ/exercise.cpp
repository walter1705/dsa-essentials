#include <iostream>
#include <numeric>
#include <algorithm>
#include <bits/stdc++.h>
using namespace std;
vector<vector<int>> pares(vector<int> a, int target) {
  vector<vector<int>> pares = {};

  size_t i = 0;
  size_t j = a.size() - 1;


  while (i != j) {
    int x = a[i];
    int y = a[j];

    if(x + y == target) {
      pares.push_back({x, y});
      i++;
    } else if(x + y > target) {
      j--;
    } else {
      i++;
    }
  }

  return pares;
}



int main () {
  vector<vector<int>> vec = pares({1, 2, 3, 4, 5, 6}, 4);
  for (const auto& par : vec) {
    cout << par[0] << " " << par[1] << endl;
  }



  return 0;
}

