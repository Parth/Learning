from flask import Flask, request

app = Flask(__name__)

@app.route('/form')
def register():
	print(request.args)

	return "hi"

app.run(debug=True)
