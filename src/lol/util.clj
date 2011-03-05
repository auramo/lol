(ns lol.util
  (:use [lol.algorithm])
  (:require [org.danlarkin.json :as json]))

(defn weight-of-item
  [item]
  (:weight item))

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
