(ns lol.runner
  (:use [lol.util]
    [lol.randomalgorithm])
  (:require [org.danlarkin.json :as json]))

(defn run-one-challenge
  [round challenge]
  (let [items (:contents challenge)
        limits (:capacity challenge)
        result (greedy-algorithm items limits)
        value (knapsack-value result)
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
  (let [files (map #(str "round" % "-config.json") '(1 2 3 4 5 6))]
    (map #(run-challenges %) files)))

