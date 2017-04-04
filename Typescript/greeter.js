function greeter(person) {
    return "Hello, " + person.firstName + " " + person.lastName;
}
var user = { firstName: "Parth", lastName: "Mehrotra" };
document.body.innerHTML = greeter(user);
