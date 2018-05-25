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
   [org.apache.axis2.client Options ServiceClient]
   [org.apache.axis2.rpc.client RPCServiceClient]))

(defn test-lambda-code []
  (let [url "http://localhost:6060/axis2/services/MyApp?wsdl"
        data "piyopiyo"
        method "changeval"
        ;; ?
        tns "http://service.pcm.com"
        ;; ===>>>
        target-epr (EndpointReference. url)
        options (doto (Options.)
                  (.setTo target-epr))
        sender (doto (ServiceClient.)
                 (.setOptions options))
        fac (OMAbstractFactory/getOMFactory)
        om-ns (.createOMNamespace fac tns "")
        ot (.createOMElement fac method om-ns)
        symbol (.createOMElement fac "name" om-ns)
        _ (.addChild symbol
                     (.createOMText fac symbol data))
        _ (.addChild ot symbol)
        ]
    (.sendReceive sender ot)
    )
  ;; org.apache.axis2.AxisFault: The input stream for an incoming message is null.
  )
