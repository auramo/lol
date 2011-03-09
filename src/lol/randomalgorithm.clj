(ns lol.randomalgorithm
  (:use [lol.algorithm] [lol.util]))

(def *randomize-percent* 30)

(defn get-tries
  [items]
  (int (* (count items) (/ *randomize-percent* 100))))

(defn scaled-int-random
  [scale]
  (int (* (Math/random) scale)))

(defn random-index
  [knapsack]
  (scaled-int-random (count knapsack)))

(defn remove-item
  [knapsack toremove]
  (remove #(= (:id %) (:id toremove)) knapsack))
  
(defn get-random-item
 [items]
 (nth items (random-index items)))

(defn remove-random-item
  [knapsack]
  (remove-item knapsack (get-random-item knapsack)))

(defn swap-random-item
  [knapsack items]
  (let [knapsack-with-random-item-removed (remove-random-item knapsack)
        new-random-item (get-random-item items)]
    (cons new-random-item knapsack-with-random-item-removed)))

(defn limits-after-base-run
  [base-knapsack limits]
  (map (fn [pair] (- (first pair) (last pair))) (map list limits (summed-weight base-knapsack))))

(defn randomly-try-changing-items
  [knapsack limits items tries-left]
  (if (= 0 tries-left)
        knapsack
        (let [new-knapsack (swap-random-item knapsack items)
              new-random-item (first new-knapsack)
              new-items (remove-item items new-random-item)
              new-limits (substract-from-limits new-random-item limits)]
          (if (and (> (knapsack-value new-knapsack) (knapsack-value knapsack))
                   (not (some (fn [x] (< x 0)) new-limits)))
        (recur new-knapsack new-limits new-items (dec tries-left))
        (recur knapsack limits new-items (dec tries-left))))))
    
(defn random-algorithm
  [items limits]
  (let [greedy-knapsack  (greedy-algorithm items limits)
        tries (get-tries items)]
    (randomly-try-changing-items greedy-knapsack (limits-after-base-run greedy-knapsack limits) items tries)))

    
