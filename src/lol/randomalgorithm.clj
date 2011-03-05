(ns lol.randomalgorithm
  (:use [lol.algorithm] [lol.util]))

(def *randomize-percent* 30)

(defn get-tries
  [knapsack]
  (* (count knapsack) (/ *randomize-percent* 100)))

(defn scaled-int-random
  [scale]
  (int (* (Math/random) scale)))

(defn random-index
  [knapsack]
  (scaled-int-random (count knapsack)))

(defn remove-item
  [knapsack toremove]
  (remove #(= (:id %) (:id toremove)) knapsack))

(defn random-item-from-knapsack
  [knapsack]
   (get knapsack (random-index knapsack)))

(defn remove-random-item
  [knapsack]
  (remove-item knapsack (random-item-from-knapsack)))
  
(defn random-item-from-items
 [items]
 (get items (random-index items)))

(defn swap-random-item
  [knapsack items]
  (let [knapsack-with-random-item-removed (remove-random-item knapsack)]
    (cons (random-item-from-items items) knapsack-with-random-item-removed)))

(defn randomly-try-changing-items
  [knapsack limits tries-left]
  (if (= 0 tries-left)
    knapsack
    (let [new-knapsack (swap-random-item knapsack)]
      (if (> (knapsack-value new-knapsack) (knapsack-value knapsack))
        (recur new-knapsack limits (dec tries-left))
        (recur knapsack limits (dec tries-left))))))
    
(defn random-algorithm
  [items limits]
  (let [greedy-knapsack  (greedy-algorithm items limits)]
    (randomly-try-changing-items(greedy-knapsack limits (get-tries(greedy-knapsack))))))
    
