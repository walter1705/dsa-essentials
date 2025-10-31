#include <iostream>
#include <numeric>
#include <algorithm>
#include <bits/stdc++.h>
using namespace std;



string resolver(int a, int b,int c, int d ) {
  bool is;  
  is = (a == b && b == c && c == d) ? true : false;

  return (is) ? "YES" : "NO";
}

int main() {
  int n;
  cin >> n;

  for (int i = 0 ; i < n ; i++) { 
    int a, b, c, d;
    cin >> a >> b >> c >> d;
    
    string message = resolver(a, b, c, d);

    cout << message << "\n";
  }

  return 0;
}



