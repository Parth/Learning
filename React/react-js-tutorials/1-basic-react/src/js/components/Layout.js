import React from "react";

export default class Layout extends React.Component {
	constructor() {
		super();
		this.name = "Parth";
	}

	render() {
		return (
			<h1>
				Hi {this.name}!
			</h1>
		);
	}
}
