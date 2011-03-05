(ns lol.util
  (:require [org.danlarkin.json :as json]))

(defn weight-of-item
  [item]
  (:weight item))

(defn negative-dimensions?
  [dimensions]
  (some (fn [x] (< x 0)) dimensions))

(defn substract-from-dimensions
  [dimensions from]
  (map (fn [pair] (- (first pair) (last pair))) (map list from dimensions)))

(defn summed-weight
  [items]
  (map
    (fn [i] (reduce + i))
    (map
      #(flatten %)
      (reduce
        (fn [left right] (map list left right))
        (map #(weight-of-item %) items)))))

(defn result-str
  [round challenge value weight limits]
  (str round ": " (:name challenge)  " value: [" value "] weight: [" (reduce (fn [a b] (str a ", " b)) weight) "]" " limits: " limits))

(defn knapsack-value
  [knapsack]
  (reduce + (map #(:value %) knapsack)))
