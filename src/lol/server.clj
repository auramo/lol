(ns lol.server
  (:use
   [ring.adapter.jetty :only [run-jetty]]
   [clojure.contrib.duck-streams :only [slurp* reader]])
  (:require [org.danlarkin.json :as json])
  (:gen-class))

(defn sort-by-value
  [items]
  (sort-by #(:value %) items))

(defn dimensions-of-item
  [item]
  (:weight item))
   
(defn substract-from-limits
  [limits item]
  (map (fn [pair] (- (first pair) (last pair))) (map list limits (dimensions-of-item item))))

(defn fill-knapsack
  [items limits knapsack]
  (let [item (first items)
        new-limits (substract-from-limits limits item)]
      (if (some (fn [x] (< x 0)) new-limits)
        knapsack
        (recur (rest items) new-limits (cons item knapsack)))))

(defn input-as-str [req]
  (slurp* (reader (:body req))))

(defn parse-json-str [json-str]
  (json/decode json-str))

(defn encode-to-json-str [structure]
  (json/encode structure))

(defn do-the-real-shit [json]
  (let [limits (:capacity json)
        items (:contents json)
        sorted-items (sort-by-value items)]
    (fill-knapsack sorted-items limits '())))

(defn do-shit
  [req]
  (do-the-real-shit (parse-json-str (input-as-str req))))

(defn app [req]
  (let [body (do-shit req)]
  {:status  200
   :headers {"Content-Type" "application/json"}
   :body    (encode-to-json-str (do-shit req))}))

(defn -main
  []
  (run-jetty app {:port 8080}))
