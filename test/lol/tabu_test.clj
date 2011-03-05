(ns lol.tabu-test
  (:use [clojure.test]
        [lol.tabu]))

(deftest test-item-on
  (let [item {:id "4"}
        mapsack {"1" {:id "1"} "2" {:id "2"} "3" {:id "3"}}
        expected {"1" {:id "1"} "2" {:id "2"} "3" {:id "3"} "4" {:id "4"}}]
    (is (= (item-on mapsack item)
           expected))))

(deftest test-item-on-for
  (let [item {:id "4"}
        mapsack {"1" {:id "1"} "2" {:id "2"} "3" {:id "3"}}
        expected {"1" {:id "1"} "2" {:id "2"} "3" {:id "3"} "4" {:id "4"}}]
    (is (= ((item-on-for item) mapsack)
           expected))))
  
(deftest test-item-off
  (let [item {:id "2"}
        mapsack {"1" {:id "1"} "2" {:id "2"} "3" {:id "3"} "4" {:id "4"}}
        expected {"1" {:id "1"} "3" {:id "3"} "4" {:id "4"}}]
    (is (= (item-off mapsack item)
           expected))))

(deftest test-item-off-for
  (let [item {:id "2"}
        mapsack {"1" {:id "1"} "2" {:id "2"} "3" {:id "3"} "4" {:id "4"}}
        expected {"1" {:id "1"} "3" {:id "3"} "4" {:id "4"}}]
    (is (= ((item-off-for item) mapsack)
           expected))))

(deftest test-mapsack-value
  (let [mapsack {"1" {:value 1} "2" {:value 2} "3" {:value 3} "4" {:value 4}}]
    (is (= (mapsack-value mapsack)
           10))))

(deftest test-objective
  (let [item1 {:id "1" :value 1}
        item5 {:id "5" :value 10}
        mapsack {(:id item1) item1 "2" {:value 2} "3" {:value 3} "4" {:value 4}}]    
    (is (= (objective mapsack (item-on-for item5))
           1))
    (is (= (objective mapsack (item-off-for item5))
           0))))