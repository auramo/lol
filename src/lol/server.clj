(ns lol.server
  (:use
    [ring.adapter.jetty :only [run-jetty]]
    [clojure.contrib.duck-streams :only [slurp* reader]]
    [lol.algorithm]
    [lol.util]
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

(defn run-algorithm
  [algorithm json]
  (let [limits (:capacity json)
        items (:contents json)
        timeout (:timeout json)
        worker (calculate algorithm items limits)]
    (await-for (- timeout 2000) worker))
  @knapsack)

(defn results-as-string
  [results]
  (reduce (fn [a b] (str a "\n\n" b )) (map (fn [n] (reduce #(str %1 "\n" %2) n)) results)))

(defn app [req]
  (if (= "/test" (:uri req))
    (let [body (results-as-string (run-rounds))]
      (clear-knapsack)
      {:status  200
       :headers {"Content-Type" "text/plain"}
       :body    body})
    (let [json (parse-json-str (input-as-str req))
          body (encode-to-json-str (items-to-id-list (run-algorithm greedy-algorithm json)))]
      (clear-knapsack)
      {:status  200
       :headers {"Content-Type" "application/json"}
       :body    body})))

(defn -main
  ([] (-main 9000))
  ([port]
    (run-jetty app {:port (Integer. port)})))
