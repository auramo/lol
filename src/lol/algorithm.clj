(ns lol.algorithm)

(defn sort-by-value
  [items]
  (sort-by (fn [x] (- 0 (:value x))) items))

(defn dimensions-of-item
  [item]
  (:weight item))

(defn substract-from-limits
  [limits item]
  (map (fn [pair] (- (first pair) (last pair))) (map list limits (dimensions-of-item item))))

(defn fill-knapsack
  ([items limits] (fill-knapsack items limits []))
  ([items limits knapsack]
  (let [item (first items)
        new-limits (substract-from-limits limits item)]
      (if (or (some #(< % 0) new-limits) (empty? items))
        knapsack
        (recur (rest items) new-limits (cons item knapsack))))))

(defn items-to-id-list
  [items]
  (map #(:id %) items))

