(ns clj-soap.steve
  (:import
   [java.io IOException]
   [javax.xml.namespace QName]
   [org.apache.axiom.om OMAbstractFactory
    OMElement
    OMFactory
    OMNamespace]
   [org.apache.axis2 AxisFault]
   [org.apache.axis2.addressing EndpointReference]
   [org.apache.axis2.client ServiceClient]
   [org.apache.axis2.rpc.client RPCServiceClient]))

#_(let [url "http://localhost:6060/axis2/services/MyApp?wsdl"
      data "piyopiyo"
      method "changeval"
      ;; ?
      tns "http://service.pcm.com"
      qn (QName. tns method)
      ]
  qn
  )
