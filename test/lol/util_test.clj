(ns lol.util_test
  (:use [clojure.test]
        [lol.util]))

(deftest test-knapsack-value
  (let [items [{:value 12} {:value 2} {:value 3}]]
    (is (= 17 (knapsack-value items)))))

(deftest test-weight-of-item
  (is (= (weight-of-item {:weight [5]})
         [5])))

(deftest test-summed-weight
  (is (= (summed-weight [{:weight [2,2,2]},{:weight [3,4,5]}])
         [5 6 7])))
                        
