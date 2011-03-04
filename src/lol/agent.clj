(ns lol.agent)

(def knapsack (agent []))

(defn max-knapsack
  [current candidate]
  (if (> (count current) (count candidate))
    current
    candidate))

(defn calculate-knapsack
  [f items limits]
  (send-off knapsack max-knapsack (f items limits)))



