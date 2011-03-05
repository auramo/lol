(ns lol.agent-test
  (:use [clojure.test]
    [lol.agent]))

(deftest test-max-knapsack
  (let [candidate [{:value 5} {:value 2} {:value 3}]]
    (is (= candidate (max-knapsack [] candidate))))
  (let [current [{:value 12} {:value 2} {:value 3}]
        candidate [{:value 5} {:value 2} {:value 3}]]
    (is (= current (max-knapsack current candidate))))
  (let [current [{:value 1} {:value 2} {:value 3}]
        candidate [{:value 5} {:value 2} {:value 3}]]
    (is (= candidate (max-knapsack current candidate)))))
