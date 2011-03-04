(ns lol.algorithm)

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

(defn items-to-id-list
  [items]
  (map #(:id %) items))
