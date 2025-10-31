#include <iostream>
#include <numeric>
#include <algorithm>
#include <bits/stdc++.h>
using namespace std;

string resolver(string str1, string str2, size_t n);

int main() {
  int num;
  cin >> num;

  for(int i = 0 ; i < num ; i++) {
    string str1, str2;
    size_t n;
    cin >> n;
    cin >> str1 >> str2;
    cout << resolver(str1, str2, n) << "\n";
  }

  return 0;
}

string resolver(string str1, string str2, size_t n) {
  vector<char> lista;

  for (size_t i = 0 ; i < n ; i++) {
    lista.push_back(str1.at(i));
  }

  for (size_t i = 0; i < n; i++) {
    auto it = find(lista.begin(), lista.end(), str2.at(i));
    if (it != lista.end()) {
      lista.erase(it);
    }
  }

  return  (lista.size() == 0) ? "YES" : "NO";
}
