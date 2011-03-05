(ns lol.tabu
  (:use lol.util))

(defn item-on
  [mapsack item]
  (assoc mapsack (:id item) item))

(defn item-off
  [mapsack item]
  (dissoc mapsack (:id item)))

(defn item-on-for
  [item]
  (fn [mapsack] (item-on mapsack item)))

(defn item-off-for
  [item]
  (fn [mapsack] (item-off mapsack item)))

(defn mapsack-value
  [mapsack]
  (knapsack-value (vals mapsack)))

(defn objective
   [mapsack move]
   (let [new-mapsack (move mapsack)]
     (if (> (mapsack-value new-mapsack)
            (mapsack-value mapsack))
       1
       0)))