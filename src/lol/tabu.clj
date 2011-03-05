(ns lol.tabu)

(defn item-on
  [knapsack item]
  (assoc knapsack (:id item) item))

(defn item-off
  [knapsack item]
  (dissoc knapsack (:id item)))