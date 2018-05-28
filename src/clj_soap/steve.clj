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

(defn test-lambda-code2 []
  ;; <soap:operation soapAction="http://report.dagene.net/GetDetailData5" style="document"/>
  (let [url "http://report.dagene.net/RasClientDetail.asmx?wsdl"
        data "31222222222"
        method "GetDetailData5"
        ;; ?
        tns "http://myclass.jp"
        ;; ===>>>
        target-epr (EndpointReference. url)
        options (doto (Options.)
                  (.setTo target-epr)
                  (.setAction "http://report.dagene.net/GetDetailData5")
                  ;; <= https://blog.csdn.net/elfenliedef/article/details/6556145
                  )
        sender (doto (ServiceClient.)
                 (.setOptions options))
        fac (OMAbstractFactory/getOMFactory)
        om-ns (.createOMNamespace fac tns "")
        ot (.createOMElement fac method om-ns)
        symbol (.createOMElement fac "ClientID" om-ns)
        ;;_ (.createOMElement fac "ClientID" om-ns) ;;OK
        ;; 在 RasClientDetail.GetDetailData5(String ClientID, String ClientGUID, String StartDate, String EndDate)
        cid   (.createOMElement fac "ClientID" om-ns)
        cuid  (.createOMElement fac "ClientGUID" om-ns)
        sdate (.createOMElement fac "StartDate" om-ns)
        edate (.createOMElement fac "EndDate" om-ns)
        _ (.setText cid "ACCCCC")
        _ (.setText cuid "ACCCCC")
        _ (.setText sdate "ACCCCC")
        _ (.setText edate "ACCCCC")
        ;;
        _ (.addChild symbol
                     (.createOMText fac symbol "aaaaaaaaaaaaa"))
        _ (.addChild ot symbol)
        ;;
        _ (.addChild ot cid)
        _ (.addChild ot cuid)
        _ (.addChild ot sdate)
        _ (.addChild ot edate)
        ]
    (.sendReceive sender ot)
    )
  ;; org.apache.axis2.AxisFault: System.Web.Services.Protocols.SoapException: 服务器未能识别 HTTP 头 SOAPAction 的值: 。

  ;; org.apache.axis2.AxisFault: System.Web.Services.Protocols.SoapException: 服务器无法处理请求。 ---> System.ArgumentNullException: 值不能为空。
  ;; 参数名: input
  )
