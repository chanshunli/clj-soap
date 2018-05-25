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
   [org.apache.axis2.client.Options ServiceClient]
   [org.apache.axis2.rpc.client RPCServiceClient]))
