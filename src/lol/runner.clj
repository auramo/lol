(ns lol.runner
  (:use [lol.util]
    [lol.algorithm]
    [lol.calculate])
  (:require [org.danlarkin.json :as json]))

(defn run-one-challenge
  [round challenge]
  (let [items (:contents challenge)
        limits (:capacity challenge)
        result (run-algorithms [greedy-algorithm] challenge)
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
  (let [files (map #(str "round" % "-config.json") (range 1 9))]
    (map #(run-challenges %) files)))

