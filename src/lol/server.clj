(ns lol.server
  (:use
   [ring.adapter.jetty :only [run-jetty]]
   [clojure.contrib.json]
   [clojure.contrib.duck-streams])
  (:gen-class))

(defn sort-by-value
  [items]
  (sort-by #(:value %) items))

(defn subtract-from-limits
  [limits item]
  (map (fn [pair] (- (first pair) (last pair))) (map list limits item)))

(defn fill-knapsack
  [items limits knapsack]
  (let [item (first items)
        new-limits (subtract-from-limits limits item)]
      (if (some (fn [x] (< x 0)) new-limits)
        knapsack
        (recur (rest items) new-limits (cons item knapsack)))))

(defn input-as-str [req]
  (slurp* (reader (:body req))))

(defn parse-json-str [json-str]
  (read-json json-str))

(defn encode-to-json-str [structure]
  (json-str structure))

(defn do-shit
  [req]
  (let [json (read-json (input-as-str req))
        limits (json "capacity")
        items (json "contents")
        sorted-items (sort-by-value items)]
    (fill-knapsack sorted-items limits '())))

(defn app [req]
  (let [body (do-shit req)]
  {:status  200
   :headers {"Content-Type" "application/json"}
   :body    (encode-to-json-str (do-shit req))}))

(defn -main
  []
  (run-jetty app {:port 8080}))
