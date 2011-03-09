(ns lol.randomalgorithm-test
  (:use [clojure.test]
        [lol.randomalgorithm]))

(deftest test-fill-knapsack
  (let [items [{:weight [10 10] :value 90} {:weight [1 1] :value 10} {:weight [2 2] :value 5}]
        limits [700 500]]
    (is (= (set items) (set (random-algorithm items limits))))))
