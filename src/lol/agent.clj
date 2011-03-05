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
        items (:contents json)]
    (calculate knapsack-agent algorithm items limits)))

(defn run-algorithms
  [algorithms json]
  (let [knapsack-agent (agent [])
        agents (map #(run-algorithm knapsack-agent % json) algorithms)
        timeout (:timeout json)]
    (apply await-for (- timeout 2000) agents)
    (await knapsack-agent)
    @knapsack-agent))
