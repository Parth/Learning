package main

import "fmt"

func main() {
	i := 10

	p := &i

	fmt.Println(i)
	fmt.Println(p)
	fmt.Println(*p)

	*p = 12
	fmt.Println(i)
	fmt.Println(p)
	fmt.Println(*p)
}
