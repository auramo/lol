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

(defn run-one-challenge 
  [round challenge]
  (let [items (:contents challenge)
        limits (:capacity challenge)
        result (greedy-algorithm items limits)
        value (reduce + (map #(:value %) result))
        weight (summed-weight result)]
    (result-str round challenge value weight limits)))

(defn run-challenges
  [file]
  (let [json-data (json/decode (slurp (str "testdata/" file)))
        round (:displayName json-data)
        challenges (:challenges json-data)]
    (map #(run-one-challenge round %) challenges)))

(defn run-rounds
  []
  (let [files '("round-config.json" "round2-config.json")]
    (map #(run-challenges %) files)))
