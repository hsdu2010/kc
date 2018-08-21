# -*- coding:utf-8 -*-
import suds
class WebServiceProvider(object):

    def __init__(self, wsdl):
        self.client = suds.client.Client(wsdl)

    def get_method_args(self, method_name):
        method = self.client.wsdl.services[0].ports[0].methods[method_name]
        input_params = method.binding.input
        params = input_params.param_defs(method)
        paramsList = []
        for i in params:
            paramsList.append(str(i[0]))
        return paramsList
        
    def get_ws_message(self):
        methods = [method for method in self.client.wsdl.services[0].ports[0].methods]
        messageBodyDict = dict()
        for method_name in methods:
            method = self.client.wsdl.services[0].ports[0].methods[method_name]
            input_params = method.binding.input
            messageBodyDict[str(method_name)] = (str(input_params.get_message(method, self.get_method_args(method_name),{})).replace("<?xml version=\"1.0\" encoding=\"UTF-8\"?>", "").strip())
        return messageBodyDict