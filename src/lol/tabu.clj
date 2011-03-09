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

(defn mapsack-weight
  [mapsack]
  (summed-weight (vals mapsack)))

(defn objective
  [mapsack move]
  (let [new-mapsack (move mapsack)
        value (mapsack-value mapsack)
        new-value (mapsack-value new-mapsack)]
    (- new-value value)))    

(defn penalty
  [mapsack limits move]
  (let [new-mapsack (move mapsack)
        new-weight (mapsack-weight new-mapsack)
        delta (substract-from-dimensions limits new-weight)]
    (max (reduce + (filter #(< 0 %) delta)) 0)))

(defn possible-item-on-moves
  [items]
  (map item-on-for items))

(defn possible-item-off-moves
  [mapsack]
  (map #(item-off-for %) (vals mapsack)))  

(defn possible-moves
  [mapsack items]
  (conj (possible-item-on-moves items) (possible-item-off-moves mapsack)))

(defn heuristic-function
  [objective penalty]
  (fn [mapsack move alpha]
    (- (objective mapsack move)
       (* (penalty mapsack move) alpha))))
