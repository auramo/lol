(ns lol.server
  (:use
   [ring.adapter.jetty :only [run-jetty]]
   [clojure.contrib.json]
   [clojure.contrib.duck-streams])
  (:gen-class))

(defn input-as-str [req]
  (slurp* (reader (:body req))))

(defn parse-json-str [json-str]
  (read-json json-str))

(defn encode-to-json-str [structure]
  (json-str structure))

(defn app [req]
  (println (type (read-json (input-as-str req))))
  {:status  200
   :headers {"Content-Type" "application/json"}
   :body    (encode-to-json-str[1,2])})

(defn -main
  []
  (run-jetty app {:port 8080}))
