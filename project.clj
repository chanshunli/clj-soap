(defproject org.clojars.seancorfield/clj-soap "0.2.1-SNAPSHOT"
  :description "SOAP Client and Server using Apache Axis2."
  :url "https://github.com/seancorfield/clj-soap"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/core.incubator "0.1.4"]
                 [org.apache.axis2/axis2-adb "1.7.7"]
                 [org.apache.axis2/axis2-transport-http "1.7.7"]
                 [org.apache.axis2/axis2-transport-local "1.7.7"]]
  :source-paths ["src" "test"]
  :aot [clj-soap.test.core]
  :java-source-paths ["java_src"]
  :main clj-soap.core)
