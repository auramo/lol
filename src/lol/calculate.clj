(ns lol.calculate
  (:use [lol.util])
  (:import [java.util.concurrent Executors TimeUnit]))

(defn max-result
  [current candidate]
  (if (> (knapsack-value current) (knapsack-value candidate))
    current
    candidate))

(defn calculate
  [result algorithm items limits]
  (swap! result max-result (algorithm items limits)))

(defn run-algorithms
  [algorithms json]
  (let [items (:contents json)
        limits (:capacity json)
        timeout (:timeout json)
        result (atom [])
        pool (Executors/newFixedThreadPool 4)
        threads (map (fn [a] (fn [] (calculate result a items limits))) algorithms)]
    (.invokeAll pool threads (- timeout 2000) (TimeUnit/SECONDS))
    (.shutdown pool)
    @result))
