(ns lol.algorithm (:use [clojure.contrib.math]))

(defn dimensions-of-item
  [item]
  (:weight item))

(defn substract-from-limits
  [item limits]
  (map (fn [pair] (- (first pair) (last pair))) (map list limits (dimensions-of-item item))))

(defn fill-knapsack
  ([items limits] (fill-knapsack items limits []))
  ([items limits knapsack]
     (if (empty? items)
       knapsack
       (let [item (first items)
             new-limits (substract-from-limits item limits)]
         (if (some (fn [x] (< x 0)) new-limits)
           (recur (rest items) limits knapsack)
           (recur (rest items) new-limits (cons item knapsack)))))))

(defn relative-value
  [item]
  (let [dimensions (dimensions-of-item item)
        dimsum (reduce #(+ %1 %2) dimensions)
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
