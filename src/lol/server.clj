(ns lol.server
  (:use
   [ring.adapter.jetty :only [run-jetty]]
   [clojure.contrib.duck-streams :only [slurp* reader]]
   [lol.algorithm])
  (:require [org.danlarkin.json :as json])
  (:gen-class))

(defn input-as-str [req]
  (slurp* (reader (:body req))))

(defn parse-json-str [json-str]
  (json/decode json-str))

(defn encode-to-json-str [structure]
  (json/encode structure))

(defn run-algorithm [algorithm json]
  (let [limits (:capacity json)
        items (:contents json)]
    (algorithm items limits)))

(defn handle-request
  [req]
  (let [json (parse-json-str (input-as-str req))
        items (run-algorithm knapsack-algorithm1 json)]
    (items-to-id-list items)))

(defn app [req]
  (let [body (encode-to-json-str (handle-request req))]
  {:status  200
   :headers {"Content-Type" "application/json"}
   :body    body}))


(defn -main
  ([] (-main 9000))
  ([port]
  (run-jetty app {:port (Integer. port)})))
