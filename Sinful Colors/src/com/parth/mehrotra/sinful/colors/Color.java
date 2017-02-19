package com.parth.mehrotra.sinful.colors;

public class Color {

	int _id;
	int _shade_id;

	public Color() {

	}

	public Color(int _id, int _shade_id) {
		this._id = _id;
		this._shade_id = _shade_id;
	}

	public Color(int _shade_id) {
		this._shade_id = _shade_id;
	}

	public int getId() {
		return this._id;
	}

	public void setId(int id) {
		this._id = id;
	}

	public int getShadeId() {
		return this._shade_id;
	}

	public void setShadeId(int id) {
		this._shade_id = id;
	}
}
