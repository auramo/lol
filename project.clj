(defproject lol "1.0.0-SNAPSHOT"
  :description "FIXME: write"
  :dependencies [[org.clojure/clojure "1.2.0"]
                 [ring/ring-jetty-adapter "0.3.6"]                 
                 [org.clojure/clojure-contrib "1.2.0"]
                 [org.danlarkin/clojure-json "1.2-SNAPSHOT"]
                 [commons-collections/commons-collections "3.2.1"]]
  :dev-dependencies [[swank-clojure "1.2.1"]]
  :main lol.server)
