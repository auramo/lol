(ns lol.client
  (:use [lol.server]
        [lol.algorithm])
  (:require [org.danlarkin.json :as json]))

(defn weight-of-item
  [item]
  (:weight item))

(defn summed-weight
  [items]
  (map (fn [i] (reduce + i)) (map #(flatten %) (reduce (fn [left right] (map list left right)) (map weight-of-item items)))))

(summed-weight [{:weight [1 2 3]} {:weight [4 2 4]}])

(defn run-one-challenge 
  [round challenge]
  (let [items (:contents challenge)
        result (run-algorithm knapsack-algorithm1 challenge)
        value (reduce + (map #(:value %) result))]
    (str round ": " (:name challenge) " value: " value)))

(defn run-challenges
  [file]
  (let [json-data (json/decode (slurp (str "testdata/" file)))
        round (:displayName json-data)
        challenges (:challenges json-data)]
    (map #(run-one-challenge round %) challenges)))

(let [files '("round-config.json" "round2-config.json")]
  (map #(run-challenges %) files))
      
      

