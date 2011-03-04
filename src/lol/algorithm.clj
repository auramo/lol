(ns lol.algorithm (:use [clojure.contrib.math]))

(defn sort-by-value
  [items]
  (sort-by #(- 0 (:value %)) items))

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

;; Algo-interface looks like this always: <algoname> items limits
(defn knapsack-algorithm1 [items limits]
  (fill-knapsack (sort-by-value items) limits))

;;Stuff for algorithm 2

(defn get-magic-weight [dimensions limits]
  (reduce + (map #(abs (- (first %) (last %))) (map list dimensions limits))))

(defn magic-predicate [item limits]
  (let [dimensions (dimensions-of-item item)
        value (:value item)
        magic-value (get-magic-weight dimensions limits)]
      (/ magic-value value)))

(defn sort-by-magic-ratio [items, limits]
  (sort-by #(magic-predicate % limits) items))

;; Yeah, pretty much copypaste from fill-knapsack -> maybe refactoring
;; needed later...
(defn algo2-impl
  ([items limits] (algo2-impl items limits []))
  ([items limits knapsack]
     (if (empty? items)
       knapsack
       (let [item (first items)
             new-limits (substract-from-limits item limits)]
         (if (some (fn [x] (< x 0)) new-limits)
           (recur (rest items) limits knapsack)
           (recur (rest items) new-limits (cons item knapsack)))))))
  

;; dimensioiden erot itseisarvo (weight[0] - limit[0]) /arvo -> mahd
;; pieni ekaks
;; jaetaan arvot
(defn knapsack-algorithm2 [items limits]
  (algo2-impl (sort-by-magic-ratio items limits) limits))

(defn items-to-id-list
  [items]
  (map #(:id %) items))
