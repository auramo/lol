(ns lol.server
  (:use
    [ring.adapter.jetty :only [run-jetty]]
    [clojure.contrib.duck-streams :only [slurp* reader]]
    [lol.algorithm]
    [lol.randomalgorithm]
    [lol.runner]
    [lol.agent])
  (:require [org.danlarkin.json :as json])
  (:gen-class))

(defn input-as-str
  [req]
  (slurp* (reader (:body req))))

(defn parse-json-str
  [json-str]
  (json/decode json-str))

(defn encode-to-json-str
  [structure]
  (json/encode structure))

(defn results-as-string
  [results]
  (reduce (fn [a b] (str a "\n\n" b )) (map (fn [n] (reduce #(str %1 "\n" %2) n)) results)))

(defn ok-response
  ([body] (ok-response body "application-json"))
  ([body content-type]
    {:status 200
     :headers {"Content-Type" content-type}
     :body body}))

(defn app [req]
  (if (= "/test" (:uri req))
    (let [body (results-as-string (run-rounds))]
      (ok-response body "text/plain"))
    (let [json (parse-json-str (input-as-str req))
          body (items-to-id-list (run-algorithms [greedy-algorithm random-algorithm] json))]
      (ok-response (encode-to-json-str body)))))

(defn -main
  ([] (-main 9000))
  ([port]
    (run-jetty app {:port (Integer. port)})))
