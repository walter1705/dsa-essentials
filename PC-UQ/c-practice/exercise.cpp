#include <iostream>
#include <numeric>
#include <algorithm>
#include <bits/stdc++.h>
using namespace std;

int main() {
  int a, b;
  cin >> a >> b; 

  vector<string> eee = {"string1", "string2", "string3"};


  //eeeee0
  reverse(eee.begin(), eee.end());

  //pila
  stack<int> pila;

  pila.push(1);
  pila.push(2);
  pila.push(3);
  cout << pila.top() << endl;

  for(size_t i = 0; i < eee.size(); i++) {
    cout << eee.at(i) << endl;
  }

  cout << "Maximo: " << max(a, b) << endl;

  cout << "Minimo: " << min(a, b) << endl;

  cout << "Greater Common Divisor: " << gcd(a, b) << endl;

  cout << "Least Common Multiple: " << lcm(a, b) << endl;

  swap(a, b);

  cout << "Swapped: " << a <<  b  << endl;

  


  
  return 0;
}

//max, min, gcd, lcm, swap, reverse
