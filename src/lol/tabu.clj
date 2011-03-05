(ns lol.tabu)

(defn item-on
  [knapsack item]
  (assoc knapsack (:id item) item))

(defn item-off
  [knapsack item]
  (dissoc knapsack (:id item)))

(defn item-on-for
  [item]
  (fn [knapsack] (item-on knapsack item)))

(defn item-off-for
  [item]
  (fn [knapsack] (item-off knapsack item)))