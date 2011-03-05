(ns lol.server
  (:use
    [ring.adapter.jetty :only [run-jetty]]
    [clojure.contrib.duck-streams :only [slurp* reader]]
    [lol.algorithm]
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

(defn app [req]
  (let [knapsack-agent (agent [])]
    (if (= "/test" (:uri req))
      (let [body (results-as-string (run-rounds))]
        {:status  200
         :headers {"Content-Type" "text/plain"}
         :body    body})
      (let [json (parse-json-str (input-as-str req))
            body (encode-to-json-str (items-to-id-list (run-algorithm knapsack-agent greedy-algorithm json)))]
        {:status  200
         :headers {"Content-Type" "application/json"}
         :body    body}))))

(defn -main
  ([] (-main 9000))
  ([port]
    (run-jetty app {:port (Integer. port)})))
