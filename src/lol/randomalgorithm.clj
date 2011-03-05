(ns lol.randomalgorithm
  (:use [lol.algorithm] [lol.util]))

(def *randomize-percent* 30)

(defn get-tries
  [knapsack]
  (int (* (count knapsack) (/ *randomize-percent* 100))))

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
  (remove-item knapsack (random-item-from-knapsack knapsack)))
  
(defn random-item-from-items
 [items]
 (get items (random-index items)))

(defn swap-random-item
  [knapsack items]
  (let [knapsack-with-random-item-removed (remove-random-item knapsack)
        new-random-item (random-item-from-items items)]
    (cons new-random-item knapsack-with-random-item-removed)))

(defn limits-after-base-run
  [base-knapsack limits]
  (map (fn [pair] (- (first pair) (last pair))) (map list limits (summed-weight base-knapsack))))

(defn randomly-try-changing-items
  [knapsack limits items tries-left]
  (if (= 0 tries-left)
        knapsack
        (let [new-knapsack (swap-random-item knapsack items)
              new-limits (substract-from-limits (first new-knapsack) limits)]
          (if (and (> (knapsack-value new-knapsack) (knapsack-value knapsack))
                   (not (some (fn [x] (< x 0)) new-limits)))
        (recur new-knapsack new-limits items (dec tries-left))
        (recur knapsack limits items (dec tries-left))))))
    
(defn random-algorithm
  [items limits]
  (let [greedy-knapsack  (greedy-algorithm items limits)
        tries (get-tries greedy-knapsack)]
    (randomly-try-changing-items greedy-knapsack (limits-after-base-run greedy-knapsack) items tries)))

    
