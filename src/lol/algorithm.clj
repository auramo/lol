(ns lol.algorithm
  (:use [clojure.contrib.math]
        [lol.util]))

(defn dimensions-of-item
  [item]
  (:weight item))

(defn substract-from-limits
  [item limits]
  (substract-from-dimensions (dimensions-of-item item) limits))

(defn fill-knapsack
  ([items limits] (fill-knapsack items limits []))
  ([items limits knapsack]
     (if (empty? items)
       knapsack
       (let [item (first items)
             new-limits (substract-from-limits item limits)]
         (if (negative-dimensions? new-limits)
           (recur (rest items) limits knapsack)
           (recur (rest items) new-limits (cons item knapsack)))))))

(defn relative-value
  [item]
  (let [dimensions (dimensions-of-item item)
        dimsum (reduce + dimensions)
        value (:value item)]
    (/ value dimsum)))

(defn sort-by-relative-value
  [items]
  (reverse (sort-by #(relative-value %) items)))

(defn greedy-algorithm
  [items limits]
  (fill-knapsack (sort-by-relative-value items) limits))

(defn items-to-id-list
  [items]
  (map #(:id %) items))
