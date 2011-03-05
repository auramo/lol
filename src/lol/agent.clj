(ns lol.agent
  (:use [lol.util]))

(def knapsack (agent []))

(defn clear-knapsack
  []
  (send knapsack (fn [x] [])))

(defn max-knapsack
  [current candidate]
  (if (> (knapsack-value current) (knapsack-value candidate))
    current
    candidate))

(defn update-knapsack
  [candidate]
  (send knapsack max-knapsack candidate))

(defn calculate
  [algorithm items limits]
  (let [worker (agent [])]
    (send worker (fn [x] (update-knapsack (algorithm items limits))))))

