(ns lol.tabu-test
  (:use [clojure.test]
        [lol.tabu]))

(deftest test-item-on
  (let [item {:id "4"}
        knapsack {"1" {:id "1"} "2" {:id "2"} "3" {:id "3"}}
        expected {"1" {:id "1"} "2" {:id "2"} "3" {:id "3"} "4" {:id "4"}}]
    (is (= (item-on knapsack item)
           expected))))

(deftest test-item-on-for
  (let [item {:id "4"}
        knapsack {"1" {:id "1"} "2" {:id "2"} "3" {:id "3"}}
        expected {"1" {:id "1"} "2" {:id "2"} "3" {:id "3"} "4" {:id "4"}}]
    (is (= ((item-on-for item) knapsack)
           expected))))
  
(deftest test-item-off
  (let [item {:id "2"}
        knapsack {"1" {:id "1"} "2" {:id "2"} "3" {:id "3"} "4" {:id "4"}}
        expected {"1" {:id "1"} "3" {:id "3"} "4" {:id "4"}}]
    (is (= (item-off knapsack item)
           expected))))

(deftest test-item-off-for
  (let [item {:id "2"}
        knapsack {"1" {:id "1"} "2" {:id "2"} "3" {:id "3"} "4" {:id "4"}}
        expected {"1" {:id "1"} "3" {:id "3"} "4" {:id "4"}}]
    (is (= ((item-off-for item) knapsack)
           expected))))
