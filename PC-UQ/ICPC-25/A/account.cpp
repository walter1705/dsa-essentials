#include <bits/stdc++.h>
using namespace std;


vector<int> getData() {
  string vec;
  getline(cin, vec);
  stringstream ss(vec);
  vector<int> v;
  int x;
  while (ss >> x) v.push_back(x);
  return v;
} 

void imprimir_vector(vector<int> l) {
  for (size_t i = 0; i < l.size(); i++) {
    cout << l[i] << " ";
  }
  cout << endl;
}

int main() {
  vector<int> ol = getData();
  int mayor = INT_MAX , menor = INT_MIN;
  size_t rango = 0;
  size_t i = 0, j = 1;
  vector<int> temporal;
  temporal.push_back(ol[0]);
  imprimir_vector(ol);

  int neg = 0, pos = 0;
  while(j < ol.size()) {
    int actual = ol[j];
    mayor = max(actual, mayor);
    menor = min(actual, menor);

    if(actual < 0) neg++;
    if(actual > 0) pos++;

    temporal.push_back(actual);
    imprimir_vector(temporal);
    if(neg == pos && actual == 0) {
      rango = max(rango, temporal.size());
    } else {
      temporal.erase(temporal.begin());
      i++;
    }
    j++;
    
  }
  
  vector<int> v = {mayor, menor, static_cast<int>(rango)};
  imprimir_vector(v);
  return 0;
}

