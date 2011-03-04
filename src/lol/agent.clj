(ns lol.agent)

(def knapsack (agent []))

(defn knapsack-value
  [items]
  (reduce #(+ %1 (:value %2)) 0 items))

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

