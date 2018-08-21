from flask import Flask
import json
from flask import request
from flask import make_response
import webserviceProvider
app = Flask(__name__)

@app.route('/getSoapMsg', methods=['GET'])
def getSoapMsg():
    wsdl = request.args.get( "wsdl", "null" )
    resp = ''
    if wsdl == '':
        resp = 'not data'
    else:
        provider = webserviceProvider.WebServiceProvider(wsdl)
        resp = json.dumps(provider.get_ws_message())
    rst = make_response(resp)
    rst.headers['Access-Control-Allow-Origin'] = '*'
    return rst, 200
    
if __name__ == '__main__':
    app.run(port=8083)