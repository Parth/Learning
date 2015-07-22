// neuron.h

#include <vector>

class Neuron{
public:
	Neuron(std::vector<Neuron*> inputs);
	void fire();

	float value;

private:
	std::vector<float> history;
	std::vector<Neuron*> inputs;
	std::vector<float> weights;

	float bias;	
};