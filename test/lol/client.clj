(ns lol.client
  (:use [lol.server]
        [lol.algorithm])
  (:require [org.danlarkin.json :as json]))

(defn run-challenges
  [file]
  (let [json-data (json/decode (slurp (str "testdata/" file)))
        round (:displayName json-data)
        challenges (:challenges json-data)]
    (map (fn [c] (str round ": " (:name c) ", items: " (map #(run-algorithm greedy-algorithm (:items %)) challenges))))))

(let [files ["round-config.json" "round2-config.json"]]
  (map #(run-challenges %) files))
      
      

