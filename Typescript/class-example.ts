class Student {
	fullName: string;
	constructor(public firstName, public middleInitial, public lastName) {
		this.fullName = firstName + " " + middleInitial + " " + lastName;
	}
}

var user = new Student("Jane", "M.", "Doe");

