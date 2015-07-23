#include <vector>
#include <iostream>

using namespace std;

void test() {
	vector<int> first(-2);
	cout << first.size() << endl;
}

int main() {
	test();
}
