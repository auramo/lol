(ns lol.server
  (:use
   [ring.adapter.jetty :only [run-jetty]]
   [clojure.contrib.json]
   [clojure.contrib.duck-streams])
  (:gen-class))

(defn sort-by-value
  [items]
  (sort-by #(% "value") items))

(defn subtract-from-limits
  [limits item]
  (map (fn [pair] (- (first pair) (last pair))) (map list limits item)))

(defn fill-knapsack
  ([items limits] (fill-knapsack items limits '()))
  ([items limits knapsack]
    (let [item (first items)
          new-limits (subtract-from-limits limits item)]
      (if (some #(% < 0) new-limits)
        knapsack
        (concat (fill-knapsack (tail items)) knapsack)))))


(defn input-as-str [req]
  (slurp* (reader (:body req))))

(defn parse-json-str [json-str]
  (read-json json-str))

(defn encode-to-json-str [structure]
  (json-str structure))

(defn app [req]
  (println (str (read-json (input-as-str req))))
  {:status  200
   :headers {"Content-Type" "application/json"}
   :body    (encode-to-json-str[1,2])})

(defn -main
  []
  (run-jetty app {:port 8080}))
