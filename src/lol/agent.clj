(ns lol.agent
  (:use [lol.util]))

(defn clear-knapsack
  [knapsack-agent]
  (send knapsack-agent (fn [x] [])))

(defn max-knapsack
  [current candidate]
  (if (> (knapsack-value current) (knapsack-value candidate))
    current
    candidate))

(defn update-knapsack
  [knapsack-agent candidate]
  (send knapsack-agent max-knapsack candidate))

(defn calculate
  [knapsack-agent algorithm items limits]
  (let [worker (agent [])]
    (send worker (fn [x] (update-knapsack knapsack-agent (algorithm items limits))))))

(defn run-algorithm
  [knapsack-agent algorithm json]
  (let [limits (:capacity json)
        items (:contents json)
        timeout (:timeout json)
        worker (calculate knapsack-agent algorithm items limits)]
    (await-for (- timeout 2000) worker)
    (await knapsack-agent)
    @knapsack-agent))